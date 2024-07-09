package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.service.dataopen.sql.AbstractSqlParser;
import org.zmz.c.service.dataopen.sql.SqlParserFactory;
import org.zmz.c.service.dataopen.sqlfunc.SqlBuilderFactory;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataSetService {

    @Resource
    DatasetParamsBuildService datasetParamsBuildService;

    @Resource
    DatasetModelService datasetModelService;

    @Resource
    AcctSqlService acctSqlService;

    @Resource
    DatasetSqlService datasetSqlService;

    // 获取 预览 SQL
    public String previewSql(DatasetDetail params) {
        String res;
        // groups 校验
        List<DatasetColumnAndConditionQo> groups = params.getGroups();
        if (CollUtil.isEmpty(groups)) {
            return "output_column_null";
        }
        // 数据同步
        params.setDatasetDetailExt(params);
        // 判断是否已经构建过path
        DatasetColumnQo columnQos = groups.stream()
                .map(DatasetColumnAndConditionQo::getColumnList)
                .flatMap(Collection::stream)
                .filter(columnQo -> CollUtil.isNotEmpty(columnQo.getPaths()))
                .findFirst()
                .orElse(null);
        if (columnQos == null) {
            // sql拼接前的参数构建
            datasetParamsBuildService.paramsBuild(params);
        }
        // 获取用到的模型
        Map<Long, ModelInfo> modelInfoMap = datasetModelService.getModelInfoMap(params);
        // 额外设置库表类型
        params.setTableType(modelInfoMap.entrySet().iterator().next().getValue().getMetaDataInfo().getTableType());
        if (Constants.SQL_PREVIEW.equalsIgnoreCase(params.getSqlMode()) && params.getGroups().size() == 1) {
            params.getGroups().get(0).setOperType(params.getOperType());
            res = previewSql(params.getGroups().get(0), modelInfoMap);
            if (params.getPageSize() != null && params.getPageSize() > 0) {
                StringBuilder sqlSb = new StringBuilder(res);
                // 单分组sql分页
                AbstractSqlParser sqlParser = SqlParserFactory.getViewSqlParser(params.getTableType());
                sqlParser.getPage(sqlSb, params.getPageIndex(), params.getPageSize());
                return sqlSb.toString();
            }
            return res;

        }
        // 保持有序
        Map<DatasetColumnAndConditionQo, String> groupSql = new LinkedHashMap<>();
        // 多分组在最外层分页
        for (DatasetColumnAndConditionQo groupQo : params.getGroups()) {
            groupQo.setPageSize(0);
            groupQo.setOperType(params.getOperType());
            res = previewSql(groupQo, modelInfoMap);
            groupSql.put(groupQo, res);
        }
        return datasetSqlService.previewGroupSql(params, groupSql);
    }

    /**
     * 预览sql
     */
    public String previewSql(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        // 校验参数
        List<DatasetColumnQo> columnList = params.getColumnList();
        if (CollectionUtils.isEmpty(columnList)) {
            return null;
        }
        if (StringUtil.isEmpty(params.getScheduleType())) {
            params.setScheduleType(acctSqlService.getDataCycle(modelInfoMap.values()));
        }

        log.info("预览sql入参: {}", JSONObject.toJSONString(params));
        log.info("模型Map入参: {}", JSONObject.toJSONString(modelInfoMap));
        String sql = SqlBuilderFactory.getSqlParse(params, modelInfoMap);
        // 将数据库带有NBSP格式的空替换为普通的空格
        sql = sql.replaceAll("\\u00A0+", " ").replaceAll(" +", " ");
        return sql;
    }
}
