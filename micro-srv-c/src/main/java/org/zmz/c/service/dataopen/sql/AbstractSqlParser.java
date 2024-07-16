package org.zmz.c.service.dataopen.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.service.dataopen.dataset.FieldTypeValueMapService;
import org.zmz.c.utils.AcctTimeUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;
import org.zmz.c.vo.dataopen.dataset.CycleInfo;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
public abstract class AbstractSqlParser {

    private static ThreadLocal<Map<String, String>> fieldTypeMapCache = new ThreadLocal<>();

    /**
     * 调度SQL标识符，当一行SQL以该标识开头时，该行SQL执行出错也会继续往下执行 用来标识一些可能会报错但没有影响的SQL，像DROP TABLE但table不存在时
     */
    protected static final String SKIP_ERROR = "/*skip=true*/";

    /**
     * 注释是否需要单独一行SQL，即comment on column 表名.字段 is '注释'
     */
    private boolean commentAloneSql = false;

    /**
     * 是否需要if exists条件
     */
    private boolean ifCond = true;

    private FieldTypeValueMapService fieldTypeValueMapService;

    protected StringBuilder builder;

    protected String dbType;

    public String getNumberTypeExp(String tableCode, String columnCode, String columnType) {
        return tableCode + "." + columnCode;
    }

    /**
     * 建表脚本
     *
     * @param schemaCode 库名
     * @param tableCode  表名
     * @param columnList 字段列表
     * @return 建表语句
     */
    public String getCreateSql(String schemaCode, String tableCode, List<Column> columnList,
                               List<Partition> partitionCols) {
        this.loadFieldTypeMap();

        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS ");
        if (StrUtil.isNotBlank(schemaCode)) {
            builder.append(schemaCode).append(".");
        }
        builder.append(tableCode).append(";");

        builder.append("CREATE TABLE ");
        if (ifCond) {
            builder.append(" IF NOT EXISTS ");
        }
        if (StrUtil.isNotBlank(schemaCode)) {
            builder.append(schemaCode).append(".");
        }
        builder.append(tableCode).append("(");
        for (Column metaColumn : columnList) {
            addCreateColumnSql(builder, metaColumn);
        }
        if (CollUtil.isNotEmpty(partitionCols)) {
            for (Partition partitionCol : partitionCols) {
                // 若分区字段未拼接，则拼接进SQL
                if (codeNotInColumnList(partitionCol.getColumnCode(), columnList)) {
                    Column metaColumn = new Column();
                    BeanUtils.copyProperties(partitionCol, metaColumn);
                    addCreateColumnSql(builder, metaColumn);
                    columnList.add(metaColumn);
                }
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(")");
        this.joinPartitionSql(builder, partitionCols);
        if (commentAloneSql) {
            this.appendColumnCommentSql(builder, schemaCode, columnList);
        }
        builder.append("; ");
        return builder.toString();
    }

    /**
     * CREATE TABLE XX.XX AS SELECT ...
     */
    public String getCreateAsSql(String schemaCode, String tableCode, String selectSql) {
        return "CREATE TABLE " + schemaCode + "." + tableCode + " AS " +
                Constants.NEW_LINE_STR + selectSql + ";";
    }

    /**
     * 生成插入数据sql
     *
     * @param schemaCode       库
     * @param tableCode        表
     * @param physicColumnList 物理表字段列表
     * @param columnList       字段列表
     * @param partitionList    分区列表
     * @return 插入数据SQL
     */
    public String getInsertSql(String schemaCode,
                               String tableCode,
                               List<Column> physicColumnList,
                               List<Column> columnList,
                               List<Partition> partitionList,
                               String tempTableCode,
                               String selectSql) {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(schemaCode).append(".").append(tableCode);
        if (CollUtil.isNotEmpty(columnList)) {
            builder.append(" (");
            List<String> columnCodes = columnList.stream().map(c -> {
                if (hasUpperCase(c.getColumnCode())) {
                    return "\"" + c.getColumnCode() + "\"";
                }
                return c.getColumnCode();
            }).collect(Collectors.toList());
            builder.append(StrUtil.join(",\n", columnCodes));
            builder.append(") ");
        }

        if (StrUtil.isNotEmpty(tempTableCode)) {
            builder.append(" SELECT ").append(Constants.NEW_LINE_STR);
            List<String> columnCodes = columnList.stream().map(Column::getColumnCode).collect(Collectors.toList());
            builder.append(StrUtil.join(",\n", columnCodes));
            builder.append(Constants.NEW_LINE_STR).append(" FROM ").append(schemaCode).append(".").append(tempTableCode)
                    .append(";");
        } else {
            builder.append(" ").append(selectSql).append(";");
        }

        return builder.toString();
    }

    /**
     * 是否需要大小写敏感处理
     */
    public boolean hasUpperCase(String columnCode) {
        return false;
    }

    public String getDropTableSql(String schemaCode, String tableCode) {
        StringBuilder builder = new StringBuilder();
        if (KeyValues.DS_WHALEHOUSE.equalsIgnoreCase(this.getTableType())) {
            builder.append("DROP VIEW ");
        } else {
            builder.append("DROP TABLE ");
        }
        if (ifCond) {
            builder.append(" IF EXISTS ");
        }
        if (StrUtil.isNotBlank(schemaCode)) {
            builder.append(schemaCode).append(".");
        }
        builder.append(tableCode);
        builder.append(";").append(Constants.NEW_LINE_STR);
        return builder.toString();
    }

    /**
     * 非必要删除，前置调度SQL标识符SKIP_ERROR，当前行SQL执行出错也会继续往下执行 不能保证表存在时可以调用当前方法，否则调用getDropTableSql方法
     *
     * @param schemaCode 库编码
     * @param tableCode  表编码
     * @return drop table语句
     */
    public String getDropTableSqlNoNecessary(String schemaCode, String tableCode) {
        return SKIP_ERROR + getDropTableSql(schemaCode, tableCode);
    }

    /**
     * 获取新增字段SQL
     *
     * @param schemaCode      库
     * @param tableCode       表
     * @param metaColumnsList 字段列表
     * @return 新增字段SQL
     */
    public abstract String getAddColumnSql(String schemaCode, String tableCode, List<Column> metaColumnsList);

    private void addCreateColumnSql(StringBuilder builder, Column metaColumn) {
        builder.append(metaColumn.getColumnCode()).append(" ").append(metaColumn.getColumnType());
        if (!commentAloneSql) {
            // mysql字段注释方式
            builder.append(" COMMENT '").append(metaColumn.getColumnName()).append("'");
        }
        builder.append(",");
    }

    /**
     * 拼接建表语句的分区字段SQL
     *
     * @param builder       builder
     * @param partitionCols 分区字段列表
     */
    abstract void joinPartitionSql(StringBuilder builder, List<Partition> partitionCols);

    private void appendColumnCommentSql(StringBuilder builder, String schemaCode, List<Column> metaColumnsList) {
        for (Column metaColumn : metaColumnsList) {
            builder.append(";").append("COMMENT ON COLUMN ");
            if (StrUtil.isNotBlank(schemaCode)) {
                builder.append(schemaCode).append(".");
            }
            builder.append(metaColumn.getTableCode()).append(".").append(metaColumn.getColumnCode()).append(" IS '")
                    .append(metaColumn.getColumnName()).append("'");
        }
    }

    public String deleteSql(String schemaCode, String tableCode, Map<String, String> whereMap) {
        StringBuilder deleteSql = new StringBuilder();
        deleteSql.append("DELETE FROM ").append(schemaCode).append(".").append(tableCode);

        if (MapUtil.isNotEmpty(whereMap)) {
            // 移除空的key
            whereMap.entrySet().removeIf(entry -> StrUtil.isBlank(entry.getKey()));
            if (MapUtil.isNotEmpty(whereMap)) {
                deleteSql.append(" WHERE ");
                int i = 0;
                for (Map.Entry<String, String> entry : whereMap.entrySet()) {
                    if (i > 0) {
                        deleteSql.append(" AND ");
                    }
                    deleteSql.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
                    i++;
                }
            }
        }
        deleteSql.append(";").append(Constants.NEW_LINE_STR);
        return deleteSql.toString();
    }

    /**
     * 针对无分区
     */
    public String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> whereMap) {
        return deleteSql(schemaCode, tableCode, whereMap);
    }

    /**
     * ALTER TABLE test_part ADD PARTITION (PARTITION d_20211202 VALUES IN (20211202));
     */
    public String addPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        return "";
    }

    /**
     * alter table test_part drop partition d_20211202; ALTER TABLE test_part ADD PARTITION (PARTITION d_20211202 VALUES
     * IN (20211202));
     */
    public String replacePartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        return dropPartitionSql(schemaCode, tableCode, partitionMap)
                + addPartitionSql(schemaCode, tableCode, partitionMap);
    }

    /**
     * 是否预先删除所有分区数据
     */
    public String replacePartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap,
                                      boolean preDel) {
        return dropPartitionSql(schemaCode, tableCode, preDel ? null : partitionMap)
                + addPartitionSql(schemaCode, tableCode, partitionMap);
    }

    /**
     * 拼接字段的长度精度表达式返回
     *
     * @param metaColumn 字段
     * @return (length, accuracy)
     */
    String getColumnLengthAccuracyDeclare(Column metaColumn) {
        if (metaColumn == null) {
            return null;
        }
        validateColumnLengthAccuracy(metaColumn);
        Integer columnLength = metaColumn.getColumnLength();
        Integer columnAccuracy = metaColumn.getColumnAccuracy();
        if (columnLength != null) {
            if (columnAccuracy != null) {
                return "(" + columnLength + ", " + columnAccuracy + ")";
            } else {
                return "(" + columnLength + ")";
            }
        }
        return "";
    }

    /**
     * 校验字段长度精度合法化
     *
     * @param metaColumn 字段
     */
    void validateColumnLengthAccuracy(Column metaColumn) {
        if (metaColumn == null) {
            return;
        }
        // 原子指标字段类型有些不支持，需要转换为合法的字段类型
        metaColumn.setColumnType(this.convertValidColumnType(metaColumn.getColumnType()));
        ColumnType columnType = this.getColumnType(metaColumn.getColumnType());
        if (columnType == null) {
            return;
        }
        // 判断字段是否不能声明长度
        if (!columnType.canDeclareLength()) {
            metaColumn.setColumnLength(null);
        }
        // 判断字段是否不能声明精度
        if (!columnType.canDeclareAccuracy()) {
            metaColumn.setColumnAccuracy(null);
        }
        // 判断字段长度是否超过限制
        if (columnType.getMaxLength() != null && metaColumn.getColumnLength() != null) {
            metaColumn.setColumnLength(
                    Math.toIntExact(Math.min(columnType.getMaxLength(), metaColumn.getColumnLength().longValue())));
        }
        // 判断字段精度是否超过限制
        if (columnType.getMaxAccuracy() != null && metaColumn.getColumnAccuracy() != null) {
            metaColumn.setColumnAccuracy(
                    Math.toIntExact(Math.min(columnType.getMaxAccuracy(), metaColumn.getColumnAccuracy().longValue())));
        }
    }

    /**
     * 字段编码是否在字段列表中已有
     *
     * @param columnCode 字段编码
     * @param columnList 字段列表
     * @return boolean
     */
    private static boolean codeInColumnList(String columnCode, List<Column> columnList) {
        if (columnCode == null || CollUtil.isEmpty(columnList)) {
            return false;
        }
        for (Column column : columnList) {
            if (columnCode.equalsIgnoreCase(column.getColumnCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字段编码不存在字段列表中
     */
    private static boolean codeNotInColumnList(String columnCode, List<Column> columnList) {
        return !codeInColumnList(columnCode, columnList);
    }

    /**
     * 从map中获取拼接分区名称 MONTH_ID_202201
     *
     * @param partitionMap MAP, key为分区字段名称 MONTH_ID,value为分区字段值 202201
     * @return 分区名称 MONTH_ID_202201
     */
    String getPartitionName(Map<String, String> partitionMap) {
        Object ptKey = partitionMap.keySet().toArray()[0];
        Object ptValue = partitionMap.get(ptKey);
        return ptKey.toString().toLowerCase() + "_" + ptValue.toString().toLowerCase();
    }

    /**
     * 加载字段映射关系到当前线程缓存中
     */
    private void loadFieldTypeMap() {
        if (fieldTypeValueMapService == null) {
            initService();
        }
        Map<String, String> fieldTypeMap = fieldTypeValueMapService.getFieldTypeMap(this.getTableType());
        fieldTypeMapCache.remove();
        fieldTypeMapCache.set(fieldTypeMap);
    }

    /**
     * 将非分布式数据库字段类型值（数字）转为实际类型名称
     *
     * @param fieldTypeValue 字段类型值，即zmgr_omp_metadata_table_field的type
     * @return 字段类型名称
     */
    String mapFieldType(String fieldTypeValue) {
        if (fieldTypeValue == null) {
            return null;
        }
        Map<String, String> fieldTypeMap = fieldTypeMapCache.get();
        if (fieldTypeMap == null) {
            this.loadFieldTypeMap();
            fieldTypeMap = fieldTypeMapCache.get();
        }
        String fieldTypeName = fieldTypeMap.get(fieldTypeValue);
        return fieldTypeName == null ? fieldTypeValue : fieldTypeName;
    }

    private synchronized void initService() {
        if (fieldTypeValueMapService == null) {
            fieldTypeValueMapService = SpringUtil.getBean("fieldTypeValueMapService");
        }
    }

    /**
     * 获取表类型，非分布式数据源表需要根据表类型查询字段类型Map
     *
     * @return 表类型
     */
    protected abstract String getTableType();

    /**
     * 获取默认字段类型，用于没有设置字段类型的字段，通常为bigint
     *
     * @return 默认字段类型
     */
    public abstract String getDefaultColumnType();

    /**
     * 获取一个字符类型的字段类型，如STRING, VARCHAR
     *
     * @return 字符类型
     */
    public abstract String getStringColumnType();

    /**
     * 根据字段类型获取ColumnType
     *
     * @param columnType 字段类型
     * @return ColumnType
     */
    protected abstract ColumnType getColumnType(String columnType);

    /**
     * 转换为合法字段类型
     *
     * @param columnType 字段类型
     * @return 合法字段类型
     */
    String convertValidColumnType(String columnType) {
        return columnType;
    }

    void setCommentAloneSql(boolean commentAloneSql) {
        this.commentAloneSql = commentAloneSql;
    }

    void setIfCond(boolean ifCond) {
        this.ifCond = ifCond;
    }

    /**
     * 分页
     */
    public void getPage(StringBuilder sql, Integer pageIndex, Integer pageSize) {
        if (null != pageSize && null != pageIndex && pageSize > 0 && pageIndex >= 0) {
            sql.append(SqlUtils.SQL_LIMIT);
            Integer offset = (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
            sql.append(offset).append(SqlUtils.STR_DOT).append(pageSize);
        }
    }

    /**
     * 默认值为 0
     */
    public String getIfNull(Column column) {
        StringBuilder outField = new StringBuilder();
        outField.append(" IFNULL(").append(column.getColumnCode()).append(",0) ");
        Integer columnAccuracy = column.getColumnAccuracy();
        if (columnAccuracy != null && columnAccuracy != 0) {
            outField.insert(0, " ROUND(");
            outField.append(",").append(columnAccuracy).append(") ");
        }
        return outField.toString();
    }

    public void getSql(StringBuilder sqlBu, String schemaCode, String tableCode, String select,
                       List<String> selectFields, List<String> wheres) {
        sqlBu.append(SqlUtils.SQL_SELECT);
        if (CollUtil.isNotEmpty(selectFields)) {
            sqlBu.append(StrUtil.join(",", selectFields));
        } else {
            sqlBu.append(select);
        }
        sqlBu.append(SqlUtils.SQL_FROM).append(schemaCode).append(SqlUtils.STR_POINT).append(tableCode);
        if (CollUtil.isNotEmpty(wheres)) {
            sqlBu.append(SqlUtils.SQL_WHERE).append(StrUtil.join(SqlUtils.SQL_AND, wheres));
        }
    }

    /**
     * 日期格式转换 date_value -> $date_format(date_value,'yyyy-MM-dd')
     */
    public String processDateValue(String value, String cycleType, String dateFormat) {
        // 如果日期格式为空，识别为日期格式的字符串，则直接加引号返回
        if (StrUtil.isEmpty(dateFormat) && isValidDate(value)) {
            return "'" + value + "'";
        } else if (StrUtil.isEmpty(dateFormat)) {
            // 如果日期格式还是为空，返回原日期的表达式toDate('20240520', 'yyyyMMDD')
            return value;
        }

        // 日期类型，不是标准账期格式的，需要转换，约定日期格式不用转换
        CycleInfo cycleInfo = AcctTimeUtil.getCycleInfo().get(cycleType);
        if (dateFormat.equals(cycleInfo.getDateFormat())) {
            return value;
        }
        value = "$date_format(" + value + "," + dateFormat + ")";

        return "'" + value + "'";
    }

    /**
     * 判断是否为日期表达式
     *
     * @param dateStr 日期表达式
     * @return boolean
     */
    public static boolean isValidDate(String dateStr) {
        try {
            if (StrUtil.isEmpty(dateStr)) {
                return false;
            }
            // 使用正则表达式匹配日期格式 'YYYY-MM-DD' 或 'YYYY-MM-DD HH:mm:ss'
            String regexDate = "\\d{4}-\\d{2}-\\d{2}";
            String regexDateTime = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
            return Pattern.matches(regexDate, dateStr) || Pattern.matches(regexDateTime, dateStr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

}
