package org.zmz.c.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zmz.c.pojo.dataopen.ZmgrMetaColumns;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.ProRestrictColumnVo;
import org.zmz.c.service.dataopen.sql.AbstractSqlParser;
import org.zmz.c.service.dataopen.sqltype.SqlBuilderHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class BuildSqlUtil {
    private BuildSqlUtil() {
    }

    public static String buildCreateColumns(List<? extends ZmgrMetaColumns> zmgrMetaColumns) {
        if (CollectionUtils.isEmpty(zmgrMetaColumns)) {
            return "";
        }
        StringBuilder partitionColumns = new StringBuilder();
        for (ZmgrMetaColumns aiMetaColumn : zmgrMetaColumns) {
            buildCreateColumns(partitionColumns, aiMetaColumn);
        }
        return partitionColumns.toString();
    }

    public static String buildCreateColumns(ZmgrMetaColumns zmgrMetaColumns) {
        return buildCreateColumns(null, zmgrMetaColumns);
    }

    public static String buildCreateColumns(StringBuilder sqlColumn, ZmgrMetaColumns zmgrMetaColumns) {
        if (sqlColumn == null) {
            sqlColumn = new StringBuilder();
        }
        appendComma(sqlColumn);
        sqlColumn.append(zmgrMetaColumns.getColumnCode()).append(" ").append(zmgrMetaColumns.getColumnType())
                .append(" COMMENT '").append(zmgrMetaColumns.getColumnName()).append("'");
        return sqlColumn.toString();
    }

    public static StringBuilder getAlterHeader() {
        return new StringBuilder("ALTER TABLE ");
    }

    public static StringBuilder getAlterAddColumnSql(String schemaCode, String tableCode) {
        return new StringBuilder("ALTER TABLE ").append(schemaCode).append(".").append(tableCode)
                .append(" ADD COLUMNS ");
    }

    /**
     * 检查规格字段是否存在 不区分大小写
     *
     * @param sourceColumns    原规格字段
     * @param targetColumnCode 目标字段编码
     * @return true/false
     */
    public static boolean contains(Collection<ZmgrMetaColumns> sourceColumns, String targetColumnCode) {
        if (sourceColumns == null || sourceColumns.isEmpty() || StringUtils.isEmpty(targetColumnCode)) {
            return false;
        }

        for (ZmgrMetaColumns sourceColumn : sourceColumns) {
            if (targetColumnCode.equalsIgnoreCase(sourceColumn.getColumnCode())) {
                return true;
            }
        }

        return false;
    }

    /**
     * 加逗号
     */
    public static void appendComma(StringBuilder source) {
        if (source == null) {
            return;
        }
        if (!source.isEmpty()) {
            source.append(",");
        }
    }

    /**
     * 拼接 hive insert 的分区字段
     *
     * @param partitions 分区字段
     * @return PARTITION(p_column)
     */
    public static String nonStrictPartitionSql(List<ZmgrMetaColumns> partitions) {
        return nonStrictPartitionSql(partitions, null);
    }

    /**
     * 拼接 hive insert 的分区字段
     *
     * @param partitions      分区字段
     * @param partitionValues 分区值顺序跟分区字段一致
     * @return PARTITION(p_column = v)
     */
    public static String nonStrictPartitionSql(List<ZmgrMetaColumns> partitions, List<String> partitionValues) {
        if (partitions == null || partitions.isEmpty()) {
            return "";
        }

        StringBuilder sql = new StringBuilder("PARTITION(");
        for (int i = 0; i < partitions.size(); i++) {
            ZmgrMetaColumns partition = partitions.get(i);
            if (sql.length() > 10) {
                sql.append(",");
            }
            sql.append(partition.getColumnCode());
            if (partitionValues != null && !partitionValues.isEmpty()) {
                sql.append(" = '").append(partitionValues.get(i)).append("'");
            }
        }
        sql.append(")");
        return sql.toString();
    }

    /**
     * 拼接select的分区字段
     *
     * @param partitions 分区字段 list
     * @return select column sql
     */
    public static StringBuilder appendPartitionsSelectColumn(String alias,
                                                             Collection<? extends ZmgrMetaColumns> partitions) {
        StringBuilder selectPartitionColumnSql = new StringBuilder();
        if (partitions == null || partitions.isEmpty()) {
            return selectPartitionColumnSql;
        }
        for (ZmgrMetaColumns partition : partitions) {
            BuildSqlUtil.appendComma(selectPartitionColumnSql);
            selectPartitionColumnSql.append(alias).append(".").append(partition.getColumnCode());
        }
        return selectPartitionColumnSql;
    }

    // ALTER TABLE js_test_del DROP IF EXISTS PARTITION (month_id = '202007');
    public static String dropPartitionSql(String schemaCode, String tableCode, String partitionCode,
                                          String partitionValue) {
        return "ALTER TABLE " + schemaCode + "." + tableCode + " DROP IF EXISTS PARTITION (" + partitionCode + " = '"
                + partitionValue + "');";
    }

    /**
     * 根据分区字段->value MAP拼接
     *
     * @param schemaCode   库
     * @param tableCode    表
     * @param partitionMap 分区字段->value
     */
    public static String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        StringBuilder dropPartitionSql = new StringBuilder("ALTER TABLE ").append(schemaCode).append(".")
                .append(tableCode).append(" DROP IF EXISTS PARTITION (");
        int i = 0;
        for (Map.Entry<String, String> entry : partitionMap.entrySet()) {
            if (i > 0) {
                dropPartitionSql.append(",");
            }
            dropPartitionSql.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
            i++;
        }
        dropPartitionSql.append(") PURGE;");
        return dropPartitionSql.toString();
    }

    public static String dropTableSql(String schemaCode, String tableCode) {
        return String.format("DROP TABLE IF EXISTS %s.%s PURGE;", schemaCode, tableCode);
    }

    // ****************************去视图 V7 版本 派生指标任务功能函数 ***************************

    /**
     * 拼接 hive insert 的分区字段
     *
     * @param partitions 分区字段
     * @return PARTITION(p_column)
     */
    public static String nonStrictPartition(List<PhysicsColumnResponseVo> partitions) {
        return nonStrictPartition(partitions, null);
    }

    /**
     * 拼接 hive insert 的分区字段
     *
     * @param partitions      分区字段
     * @param partitionValues 分区值顺序跟分区字段一致
     * @return PARTITION(p_column = v)
     */
    public static String nonStrictPartition(List<PhysicsColumnResponseVo> partitions, List<String> partitionValues) {
        if (partitions == null || partitions.isEmpty()) {
            return "";
        }

        StringBuilder sql = new StringBuilder("PARTITION(");
        for (int i = 0; i < partitions.size(); i++) {
            PhysicsColumnResponseVo partition = partitions.get(i);
            if (sql.length() > 10) {
                sql.append(",");
            }
            sql.append(partition.getColumnCode());
            if (partitionValues != null && !partitionValues.isEmpty()) {
                sql.append(" = '").append(partitionValues.get(i)).append("'");
            }
        }
        sql.append(")");
        return sql.toString();
    }

    /**
     * 业务限定拼接条件
     */
    public static void appendSimpleCond(StringBuilder whereBuilder, ProRestrictColumnVo restrictColumnVo,
                                        AbstractSqlParser abstractSqlParser) {
        appendSimpleCond(whereBuilder, restrictColumnVo.getCondType(), restrictColumnVo.getCondOperator(),
                restrictColumnVo.getCondValue(), restrictColumnVo.getTableCode(), restrictColumnVo.getColumnCode(),
                restrictColumnVo.getColumnType(), restrictColumnVo.getCycleType(), restrictColumnVo.getDateFormat(),
                restrictColumnVo.getIsDynamic(), abstractSqlParser);
    }

    /**
     * 全局条件/度量条件拼接条件
     */
    public static void appendSimpleCond(StringBuilder whereBuilder, DatasetConditionQo datasetConditionQo, String isSql,
                                        AbstractSqlParser abstractSqlParser) {
        appendSimpleCond(whereBuilder, datasetConditionQo.getCondType(), datasetConditionQo.getCondOperator(),
                datasetConditionQo.getCondValue(), datasetConditionQo.getTableAlias(), datasetConditionQo.getColumnCode(),
                datasetConditionQo.getColumnType(), datasetConditionQo.getCycleType(), datasetConditionQo.getDateFormat(),
                isSql, abstractSqlParser);
    }

    public static void appendSimpleCond(StringBuilder whereBuilder, String condType, String condOperator,
                                        String condValue, String tableCode, String columnCode, String columnType, String cycleType, String dataFormat,
                                        String isSql, AbstractSqlParser abstractSqlParser) {
        // 连接符，括号，算术运算符
        if ("connectOpt".equals(condType) || "bracket".equals(condType) || "arithmeticOpt".equals(condType)) {
            whereBuilder.append(" ").append(condValue).append(" ");
        } else if ("arithmeticCondItem".equals(condType)) {
            whereBuilder.append(tableCode).append(SqlUtils.STR_POINT).append(columnCode).append(" ");
            // 算术运算条件
        }
        // 普通条件
        else if (StringUtil.isNotEmpty(condOperator)) {
            // 自定义sql不需要拼字段，预留，暂未用到
            if ("simpleCond".equals(condType) && !"SQL".equalsIgnoreCase(condOperator)) {
                whereBuilder.append(tableCode).append(".").append(columnCode);
            }
            // 拼接条件
            appendSimpleCond(whereBuilder, condOperator, condValue, columnType, cycleType, dataFormat, isSql,
                    abstractSqlParser);
        }
    }

    public static void appendSimpleCond(StringBuilder whereBuilder, String condOperator, String condValue,
                                        String columnType, String cycleType, String dataFormat, String isSql, AbstractSqlParser abstractSqlParser) {
        // 拼接条件
        // 有date类型的字段条件，需要格式化日期
        if (columnType.toLowerCase().contains("date") && StringUtil.isNotEmpty(dataFormat)
                && !KeyValues.YES_VALUE_1.equals(isSql) && !"SQL".equalsIgnoreCase(condOperator)
                && !"EXPRESSION".equalsIgnoreCase(condOperator)) {
            // 分割值
            List<String> condValues = new ArrayList<>();
            String[] splitArray;
            if (condValue.contains("~")) {
                splitArray = condValue.split("~");
            } else {
                splitArray = condValue.split(",");
            }
            for (String split : splitArray) {
                condValues.add(abstractSqlParser.processDateValue(split, cycleType, dataFormat));
            }
            condValue = StringUtils.join(condValues, "~");
            BuildSqlUtil.appendSimpleCond(whereBuilder, condOperator, condValue, false);
        } else {
            BuildSqlUtil.appendSimpleCond(whereBuilder, condOperator, condValue,
                    SqlBuilderHelper.isStringType(columnType));
        }
    }

    public static void appendSimpleCond(StringBuilder whereBuilder, String condOperator, String condValue) {
        appendSimpleCond(whereBuilder, condOperator, condValue, true);
    }

    /**
     * 条件值是否增加单引号
     *
     * @param whereBuilder
     * @param condOperator
     * @param condValue
     * @param addQuot
     */
    public static void appendSimpleCond(StringBuilder whereBuilder, String condOperator, String condValue,
                                        boolean addQuot) {
        if (StringUtil.isNotEmpty(condOperator)) {
            String quot = addQuot ? "'" : "";
            // 拼接条件 arithmeticCond
            if ("NOT IN".equalsIgnoreCase(condOperator) || "IN".equalsIgnoreCase(condOperator)) {
                whereBuilder.append(" ").append(condOperator).append(" ");
                // 去掉多余的逗号
                if (condValue.endsWith(",")) {
                    condValue = condValue.substring(0, condValue.length() - 1);
                }
                // 分割IN后面的值
                String[] splitArray = condValue.split(",");
                StringBuffer buf = new StringBuffer();
                for (int i = 0; i < splitArray.length; i++) {
                    buf.append(quot).append(splitArray[i].trim()).append(quot);
                    if (i != splitArray.length - 1) {
                        buf.append(",");
                    }
                }
                String newRuleValue = buf.toString();
                whereBuilder.append("(").append(newRuleValue).append(")").append(" ");
            } else if ("LIKE".equals(condOperator) || "NOT LIKE".equals(condOperator)) {
                whereBuilder.append(" ").append(condOperator).append(" ");
                whereBuilder.append("'%").append(condValue).append("%'").append(" ");
            } else if ("LLIKE".equals(condOperator)) {
                whereBuilder.append(" LIKE ");
                whereBuilder.append("'").append(condValue).append("%'").append(" ");
            } else if ("RLIKE".equals(condOperator)) {
                whereBuilder.append(" LIKE ");
                whereBuilder.append("'%").append(condValue).append("' ");
            } else if ("IS NULL".equals(condOperator) || "IS NOT NULL".equals(condOperator)) {
                whereBuilder.append(" ").append(condOperator).append(" ");
            } else if ("NOT BETWEEN".equals(condOperator) || condOperator.startsWith("BETWEEN")) {
                // 适配动态时间（BETWEEN_DYNAMIC）、相对时间
                if (condOperator.startsWith("BETWEEN")) {
                    condOperator = "BETWEEN";
                }
                whereBuilder.append(" ").append(condOperator).append(" ");
                // 分割后面的值
                String[] splitArray;
                if (condValue.contains("~")) {
                    splitArray = condValue.split("~");
                } else {
                    splitArray = condValue.split(",");
                }
                for (int i = 0; i < splitArray.length; i++) {
                    String str = splitArray[i];
                    splitArray[i] = quot + str + quot;
                }
                String joinStr = StringUtil.join(" AND ", splitArray);
                whereBuilder.append(joinStr).append(" ");
            }
            // 自定义表达式,比如：[a.col] %100 = 0
            else if ("EXPRESSION".equals(condOperator)) {
                whereBuilder.append(" ").append(condValue).append(" ");
            }
            // 自定义sql,手写sql
            else if ("SQL".equals(condOperator)) {
                whereBuilder.append(" ").append(condValue).append(" ");
            } else {
                whereBuilder.append(" ").append(condOperator).append(" ");
                // 字符串处理
                whereBuilder.append(handleQuotCondValue(condValue, quot));
            }
        }
    }

    /**
     * 符号拼接，处理是是否是动态sql
     *
     * @return String
     */
    private static String handleQuotCondValue(String condValue, String quot) {
        boolean dynamicSql = CheckStringUtil.isDynamicSql(condValue);
        if (dynamicSql) {
            return condValue;
        } else if (StringUtil.isNotEmpty(quot)) {
            return quot.concat(condValue).concat(quot);
        }
        return condValue;
    }

    public static void appendSimpleCondOfPeriod(StringBuilder whereBuilder, String[] resultPeriod, boolean isStr) {
        if (StringUtil.isNotEmpty(resultPeriod[0])) {
            // 拼接条件 arithmeticCond
            if ("NOT IN".equalsIgnoreCase(resultPeriod[0]) || "IN".equalsIgnoreCase(resultPeriod[0])) {
                whereBuilder.append(" ").append(resultPeriod[0]).append(" ");
                StringBuffer buf = new StringBuffer();
                for (int i = 1; i < resultPeriod.length; i++) {
                    if (isStr) {
                        buf.append("'").append(resultPeriod[i].trim()).append("'");
                    } else {
                        buf.append(resultPeriod[i].trim());
                    }
                    if (i != resultPeriod.length - 1) {
                        buf.append(",");
                    }
                }
                String newRuleValue = buf.toString();
                newRuleValue = SqlBuilderHelper.replaceString(newRuleValue, isStr);
                whereBuilder.append("(").append(newRuleValue).append(")").append(" ");
            } else if ("BETWEEN".equals(resultPeriod[0]) || "NOT BETWEEN".equals(resultPeriod[0])) {
                // 适配动态时间（BETWEEN_DYNAMIC）、相对时间
                whereBuilder.append(" ").append(resultPeriod[0]).append(" ");
                // 分割后面的值
                String[] splitArray = new String[resultPeriod.length - 1];
                for (int i = 0; i < resultPeriod.length - 1; i++) {
                    if (isStr) {
                        splitArray[i] = "'" + resultPeriod[i + 1] + "'";
                    } else {
                        splitArray[i] = resultPeriod[i + 1];
                    }
                }
                String joinStr = StringUtil.join(" AND ", splitArray);
                whereBuilder.append(joinStr).append(" ");
            }

        }
    }
}