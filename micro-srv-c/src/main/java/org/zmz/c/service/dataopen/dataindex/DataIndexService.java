package org.zmz.c.service.dataopen.dataindex;

import static org.zmz.c.utils.SqlUtils.SQL_AND;
import static org.zmz.c.utils.SqlUtils.SQL_AS;
import static org.zmz.c.utils.SqlUtils.SQL_FROM;
import static org.zmz.c.utils.SqlUtils.SQL_LEFT_JOIN;
import static org.zmz.c.utils.SqlUtils.SQL_ON;
import static org.zmz.c.utils.SqlUtils.SQL_SELECT;
import static org.zmz.c.utils.SqlUtils.STR_BLANK;
import static org.zmz.c.utils.SqlUtils.STR_DOT;
import static org.zmz.c.utils.SqlUtils.STR_EQUAL;
import static org.zmz.c.utils.SqlUtils.STR_POINT;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.zmz.c.mapper.dataopen.ObjKeyTableRelaMapper;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.qo.dataopen.ObjKeyColumnRelaVo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaQo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zmz
 */
@Slf4j
@Service
public class DataIndexService {

    @Resource
    ObjKeyTableRelaMapper objKeyTableRelaMapper;

    private static final AtomicInteger TB_COUNTER = new AtomicInteger(0);

    public String generateSql(DatasetDetail params) {
        DatasetColumnAndConditionQo group = params.getGroups().get(0);
        List<DatasetColumnQo> columnList = group.getColumnList();
        // 从 columnList 中构建 查询输出字段
        StringBuilder outField = new StringBuilder(SQL_SELECT);
        Map<String, String> tbMap = this.buildOutField(outField, columnList);
        // 构建 from
        this.buildFrom(outField, tbMap, columnList);
        return outField.toString();
    }

    private void buildFrom(StringBuilder outField, Map<String, String> tbMap, List<DatasetColumnQo> columnList) {
        outField.append(SQL_FROM);
        List<ObjKeyTableRelaVo> objKeyTableRelaVos = qryTableFieldOnCondition(columnList);
        joinOn(outField, tbMap, objKeyTableRelaVos);
    }

    private Map<String, String> buildOutField(StringBuilder outField, List<DatasetColumnQo> columnList) {
        Map<String, String> tbAliasMap = new LinkedHashMap<>(columnList.size());
        int i = 0;
        for (DatasetColumnQo datasetColumnQo : columnList) {
            String datasourceCode = datasetColumnQo.getDatasourceCode();
            String tableCode = datasetColumnQo.getTableCode();
            String tbAlias = cacheTableAlias(tbAliasMap, datasourceCode, tableCode);
            String alias = datasetColumnQo.getAlias();
            String columnCode = datasetColumnQo.getColumnCode();
            outField.append(tbAlias).append(STR_POINT).append(columnCode).append(SQL_AS).append(alias);
            if (i != columnList.size() - 1) {
                outField.append(STR_DOT);
                i++;
            }
            // 输出字段注释
            getFieldComments(outField, datasetColumnQo);
        }
        // 归 0
        TB_COUNTER.set(0);
        return tbAliasMap;
    }

    private String sqlFieldComments(String field) {
        return String.format("/**%s*/ ", field);
    }

    private void getFieldComments(StringBuilder sb, DatasetColumnQo qo) {
        if (qo.getDataName() != null) {
            String fieldComment = this.sqlFieldComments(qo.getDataName());
            sb.append(fieldComment);
            return;
        }
        if (qo.getColumnName() != null) {
            String fieldComment = this.sqlFieldComments(qo.getColumnName());
            sb.append(fieldComment);
            return;
        }
        if (qo.getColumnDesc() != null) {
            String fieldComment = this.sqlFieldComments(qo.getColumnDesc());
            sb.append(fieldComment);
        }
    }

    // {"datasourceCode.tableCode":"tb1"}
    private String cacheTableAlias(Map<String, String> tbAliasMap, String datasourceCode, String tableCode) {
        String key = datasourceCode + STR_POINT + tableCode;
        if (tbAliasMap.containsKey(key)) {
            return tbAliasMap.get(key);
        }
        String tbAlias = generateTableAlias();
        tbAliasMap.put(key, tbAlias);
        return tbAlias;
    }

    private String generateTableAlias() {
        return "tb" + TB_COUNTER.incrementAndGet();
    }

    // 根据 objectIds
    public List<ObjKeyTableRelaVo> qryTableFieldOnCondition(List<DatasetColumnQo> columnList) {
        Set<Long> objectIdSets = columnList.stream().map(DatasetColumnQo::getObjectId).collect(Collectors.toSet());
        ObjKeyTableRelaQo objKeyTableRelaQo = new ObjKeyTableRelaQo();
        objKeyTableRelaQo.setObjectIds(new ArrayList<>(objectIdSets));

        return objKeyTableRelaMapper.selectKeyRelaByQo(objKeyTableRelaQo);
    }

    //
    private void joinOn(StringBuilder sb, Map<String, String> tbMap, List<ObjKeyTableRelaVo> objKeyTableRelaVos) {
        if (CollectionUtils.isNotEmpty(objKeyTableRelaVos)) {
            int i = 0;
            for (ObjKeyTableRelaVo vo : objKeyTableRelaVos) {
                String datasourceCode = vo.getDatasourceCode();
                String tableCode = vo.getTableCode();

                String relaTableCode = vo.getRelaTableCode();

                String sourceKey = datasourceCode + STR_POINT + tableCode;
                String sourceTbAlias = tbMap.get(sourceKey);
                String relaKey = datasourceCode + STR_POINT + relaTableCode;
                String relaTbAlias = tbMap.get(relaKey);

                sb.append(sourceKey).append(STR_BLANK).append(sourceTbAlias);
                sb.append(SQL_LEFT_JOIN);
                i++;
                sb.append(relaKey).append(STR_BLANK).append(relaTbAlias);
                sb.append(SQL_ON);

                List<ObjKeyColumnRelaVo> keyColumnRelas = vo.getKeyColumnRelas();

                if (CollectionUtils.isNotEmpty(keyColumnRelas) && StringUtils.isNotBlank(sourceTbAlias)
                    && StringUtils.isNotBlank(relaTbAlias)) {
                    int j = 1;
                    for (ObjKeyColumnRelaVo keyColumnRela : keyColumnRelas) {
                        String columnCode = keyColumnRela.getColumnCode();
                        String relaColumnCode = keyColumnRela.getRelaColumnCode();

                        sb.append(sourceTbAlias).append(STR_POINT).append(columnCode).append(STR_EQUAL)
                            .append(relaTbAlias).append(STR_POINT).append(relaColumnCode);
                        if (j != keyColumnRelas.size()) {
                            sb.append(SQL_AND);
                            j++;
                        }
                    }
                }

                if (i != objKeyTableRelaVos.size()) {
                    sb.append(SQL_LEFT_JOIN);
                }
            }
        }
    }
}
