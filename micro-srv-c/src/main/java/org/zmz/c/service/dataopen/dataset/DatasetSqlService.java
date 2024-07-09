package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.service.dataopen.sql.AbstractSqlParser;
import org.zmz.c.service.dataopen.sql.SqlParserFactory;
import org.zmz.c.utils.SqlUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DatasetSqlService {

    private static final String TABLE_ALIAS_PREFIX = "g";

    @Resource
    DatasetGroupService datasetGroupService;

    @Resource
    DatasetDataPrivService datasetDataPrivService;

    public String previewGroupSql(DatasetDetail params, Map<DatasetColumnAndConditionQo, String> groupSql) {
        if (Constants.SQL_TASK.equalsIgnoreCase(params.getSqlMode())) {
            return StrUtil.join(",", groupSql.values());
        } else {
            return buildPreviewGroupSql(params, groupSql, null);
        }
    }

    public String buildPreviewGroupSql(DatasetDetail params, Map<DatasetColumnAndConditionQo, String> groupSql,
                                       String acctColumnCode) {
        // 最细粒度，用来关联字段
        List<DatasetColumnQo> smallestDimensions = datasetGroupService.getSmallestDimensions(params);
        // 表别名
        int i = 1;
        // select字段
        List<String> allCols = new ArrayList<>();
        // 去重用
        Set<String> allColCodes = new HashSet<>();
        // 拼接分组sql select [allcols] from sql1 t1 left join sql2 t2 on t1.最细粒度=t2.最细粒度
        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append(SqlUtils.SQL_FROM);

        // 多分组也可能有层级维度汇总 todo

        // 每个分组都有consumeOrgId，且最细粒度包含组织字段才需要分组关联
        boolean groupByConsumeOrgId = datasetDataPrivService.getDataPrivGroupCount(params) == params.getGroups().size()
                && smallestDimensions.stream().anyMatch(DatasetColumnQo::isOrgDimensionCol);

        for (Map.Entry<DatasetColumnAndConditionQo, String> entry : groupSql.entrySet()) {
            DatasetColumnAndConditionQo groupQo = entry.getKey();
            String sql = entry.getValue();

            for (DatasetColumnQo columnQo : groupQo.getColumnList()) {
                if (!columnQo.isHide() && allColCodes.add(columnQo.getAlias())) {
                    allCols.add(TABLE_ALIAS_PREFIX + i + "." + columnQo.getAlias());
                    // 维度枚举值字段
                }
            }
            // 组织字段，组织路径
            DataPrivCtrlVo dataPrivCtrlInfo = groupQo.getDataPrivCtrlInfo();
            if (dataPrivCtrlInfo.isDataPrivCtrl()) {
                // 取到有组织权限字段，则添加到输出，只取一次
                if (allColCodes.add(Constants.DATA_PRIV_PATH_CODE)) {
                    allCols.add(0, TABLE_ALIAS_PREFIX + i + "." + Constants.DATA_PRIV_PATH_CODE);
                    // 组织id，优先addOrgDimensionColumn，其次existOrgDimensionColumn
                    if (dataPrivCtrlInfo.getAddOrgDimensionColumn() != null) {
                        if (allColCodes.add(Constants.DATA_PRIV_ORG_FIELD_CODE)) {
                            // 增加的组织id字段约定别名为consume_org_id
                            allCols.add(0, TABLE_ALIAS_PREFIX + i + "." + Constants.DATA_PRIV_ORG_FIELD_CODE);
                        }
                    } else if (dataPrivCtrlInfo.getExistOrgDimensionColumn() != null) {
                        if (allColCodes.add(dataPrivCtrlInfo.getExistOrgDimensionColumn().getAlias())) {
                            allCols.add(0, TABLE_ALIAS_PREFIX + i + "."
                                    + dataPrivCtrlInfo.getExistOrgDimensionColumn().getAlias());
                        }
                    } else {
                        if (allColCodes.add(Constants.DATA_PRIV_ORG_FIELD_CODE)) {
                            allCols.add(0, TABLE_ALIAS_PREFIX + i + "." + Constants.DATA_PRIV_ORG_FIELD_CODE);
                        }
                    }
                }
            }

            if (i > 1) {
                sqlSb.append(SqlUtils.SQL_LEFT_JOIN);
            }
            sqlSb.append(SqlUtils.STR_LEFT_BRACKET).append(sql).append(SqlUtils.STR_RIGHT_BRACKET)
                    .append(TABLE_ALIAS_PREFIX).append(i);
            if (i > 1) {
                sqlSb.append(SqlUtils.SQL_ON);
                for (DatasetColumnQo smallestDimension : smallestDimensions) {
                    // 最细粒度字段不允许修改和删除
                    sqlSb.append(TABLE_ALIAS_PREFIX).append(i - 1).append(SqlUtils.STR_POINT)
                            .append(smallestDimension.getAlias()).append(SqlUtils.STR_EQUAL).append(TABLE_ALIAS_PREFIX)
                            .append(i).append(SqlUtils.STR_POINT).append(smallestDimension.getAlias())
                            .append(SqlUtils.SQL_AND);
                }
                // 如果是组织层级字段非主键，要加上主键关联条件
                if (groupByConsumeOrgId && groupQo.getDataPrivCtrlInfo().getAddOrgDimensionColumn() != null) {
                    sqlSb.append(TABLE_ALIAS_PREFIX).append(i - 1).append(SqlUtils.STR_POINT)
                            .append(Constants.DATA_PRIV_ORG_FIELD_CODE).append(SqlUtils.STR_EQUAL)
                            .append(TABLE_ALIAS_PREFIX).append(i).append(SqlUtils.STR_POINT)
                            .append(Constants.DATA_PRIV_ORG_FIELD_CODE).append(SqlUtils.SQL_AND);
                }
                // 去掉最后一个AND
                sqlSb.delete(sqlSb.length() - 4, sqlSb.length());
            }
            i++;
        }

        // 账期字段
        if (StrUtil.isNotEmpty(acctColumnCode) && allColCodes.add(acctColumnCode)) {
            allCols.add(0, TABLE_ALIAS_PREFIX + 1 + "." + acctColumnCode);
        }
        /*
         * // 周期性预览拼接账期条件 if (StringUtils.isEmpty(acctColumnCode) &&
         * !Constants.SCHEDULE_LOOP_TYPE_O.equals(params.getScheduleType())) { if ("D".equals(params.getScheduleType()))
         * { allCols.add(0, "${day_id} as day_id"); } else { allCols.add(0, "${month_id} as month_id"); } }
         */

        sqlSb.insert(0, StrUtil.join(",", allCols));
        sqlSb.insert(0, SqlUtils.SQL_SELECT);

        if (params.getPageSize() != null && params.getPageSize() > 0) {
            // 分页
            AbstractSqlParser sqlParser = SqlParserFactory.getViewSqlParser(params.getTableType());
            sqlParser.getPage(sqlSb, params.getPageIndex(), params.getPageSize());
        }

        return sqlSb.toString();
    }
}
