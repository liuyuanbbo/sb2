package org.zmz.c.service.dataopen.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zmz.c.pojo.dataopen.AttrValue;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.service.dataopen.common.StaticDataService;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.utils.BuildSqlUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.zmz.c.service.dataopen.sqlenum.HiveStorageInputFormatEnum.PARQUET;
import static org.zmz.c.service.dataopen.sqlenum.HiveStorageInputFormatEnum.TEXTFILE;

@Getter
@Setter
public final class ObjHiveSqlParser extends AbstractSqlParser {

    private static final long serialVersionUID = -13591082663816872L;

    /**
     * 实例
     */
    private static ObjHiveSqlParser instance;

    private ObjHiveSqlParser() {
    }

    public static ObjHiveSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjHiveSqlParser();
        }
    }

    @Override
    public String getCreateSql(String schemaCode, String tableCode, List<Column> columnList,
                               List<Partition> partitionCols) {
        StringBuilder builder = new StringBuilder();
        String storedFormat = PARQUET.name();
        String rowFormat = "";
        builder.append("CREATE TABLE IF NOT EXISTS ");

        builder.append(schemaCode).append(".").append(tableCode);

        // 字段
        if (CollUtil.isNotEmpty(columnList)) {
            boolean isSame = false;
            boolean isExistField = false;
            builder.append(" ( ");

            for (Column metaColumn : columnList) {
                if (CollUtil.isNotEmpty(partitionCols)) {
                    for (Partition bdMetaParVo : partitionCols) {
                        if (metaColumn.getColumnCode().equals(bdMetaParVo.getColumnCode())) {
                            isSame = true;
                            break;
                        }
                    }
                }

                if (!isSame) {
                    if (isExistField) {
                        builder.append(",");
                    }
                    builder.append(metaColumn.getColumnCode()).append(" ").append(metaColumn.getColumnType());
                    if (StringUtils.isNotEmpty(metaColumn.getColumnName())) {
                        builder.append(" ").append("COMMENT").append(" ").append("'").append(metaColumn.getColumnName())
                                .append("'");
                    }

                    isExistField = true;
                }
                isSame = false;
            }
            builder.append(" ) ");
        }

        // 分区字段
        if (CollUtil.isNotEmpty(partitionCols)) {
            builder.append(" PARTITIONED BY (");
            int partitionNum = partitionCols.size();

            for (int j = 0; j < partitionNum; ++j) {
                Partition partitionColumn = partitionCols.get(j);
                builder.append(partitionColumn.getColumnCode()).append(" ").append(partitionColumn.getPartitionType());

                for (Column metaColumn : columnList) {
                    if (metaColumn.getColumnCode().equals(partitionColumn.getColumnCode())
                            && StringUtils.isNotEmpty(metaColumn.getColumnName())) {
                        builder.append(" ").append("COMMENT").append(" ").append("'").append(metaColumn.getColumnName())
                                .append("'");
                        break;
                    }
                }
                if (j != partitionNum - 1) {
                    builder.append(",");
                }
            }
            builder.append(" ) ");
        }

        if (StringUtils.isNotBlank(rowFormat) && TEXTFILE.name().equalsIgnoreCase(storedFormat)) {
            // 只有TEXTFILE可以设置rowFormat
            builder.append(" ROW FORMAT DELIMITED FIELDS TERMINATED BY '").append(rowFormat).append("'");
        }
        if (StringUtils.isNotBlank(storedFormat)) {
            builder.append(" STORED AS ").append(storedFormat);
        }
        builder.append("; ");
        return builder.toString();
    }

    @Override
    public String getAddColumnSql(String schemaCode, String tableCode, List<Column> metaColumnsList) {
        if (schemaCode == null || StringUtils.isEmpty(tableCode) || CollectionUtils.isEmpty(metaColumnsList)) {
            return null;
        }
        StringBuilder alterSql = BuildSqlUtil.getAlterAddColumnSql(schemaCode, tableCode).append("(");
        for (Column metaColumn : metaColumnsList) {
            alterSql.append(metaColumn.getColumnCode()).append(" ").append(metaColumn.getColumnType())
                    .append(" COMMENT '").append(metaColumn.getColumnName()).append("',");
        }
        alterSql.deleteCharAt(alterSql.length() - 1);
        alterSql.append(")");
        return alterSql.toString();
    }

    /**
     * 拼接建表语句的分区字段SQL
     *
     * @param builder       builder
     * @param partitionCols 分区字段列表
     */
    @Override
    void joinPartitionSql(StringBuilder builder, List<Partition> partitionCols) {
    }

    @Override
    public String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        if (MapUtil.isEmpty(partitionMap)) {
            return "";
        }
        StringBuilder dropPartitionSql = new StringBuilder("ALTER TABLE ").append(schemaCode).append(".")
                .append(tableCode).append(" DROP IF EXISTS PARTITION ");
        if (MapUtil.isNotEmpty(partitionMap)) {
            dropPartitionSql.append("(");
            int i = 0;
            for (Map.Entry<String, String> entry : partitionMap.entrySet()) {
                if (i > 0) {
                    dropPartitionSql.append(",");
                }
                dropPartitionSql.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
                i++;
            }
            dropPartitionSql.append(") PURGE");
        }
        dropPartitionSql.append(";").append(Constants.NEW_LINE_STR);
        return dropPartitionSql.toString();
    }

    /**
     * 生成插入数据sql
     *
     * @param schemaCode 库
     * @param tableCode  表
     * @param columnList 物理表字段列表
     * @param columnList 字段列表
     * @return 插入数据SQL
     */
    @Override
    public String getInsertSql(String schemaCode, String tableCode, List<Column> physicColumnList,
                               List<Column> columnList, List<Partition> partitionList, String tempTableCode, String selectSql) {
        StringBuilder insertSql = new StringBuilder();

        // 拼接hive动态分区配置命令
        if (CollUtil.isNotEmpty(partitionList)) {
            StaticDataService staticDataService = SpringUtil.getBean(StaticDataService.class);
            List<AttrValue> attrValues = staticDataService.getAttrValueByCode("HIVE_SQL_SET");
            if (CollUtil.isNotEmpty(attrValues)) {
                // SET hive.map.aggr=true;SET hive.groupby.skewindata=true;
                // 优化group by 产生的数据倾斜
                List<String> setSql = attrValues.stream()
                        .map(attr -> attr.getAttrValue().endsWith(";")
                                ? attr.getAttrValue().substring(0, attr.getAttrValue().length() - 1)
                                : attr.getAttrValue()).collect(Collectors.toList());
                insertSql.append(StringUtils.join(setSql, ";" + Constants.NEW_LINE_STR)).append(";")
                        .append(Constants.NEW_LINE_STR);
            }
        }

        insertSql.append("INSERT INTO TABLE ");
        insertSql.append(schemaCode).append(".").append(tableCode);
        // 分区
        List<String> partitions = new ArrayList<>();
        if (CollUtil.isNotEmpty(partitionList)) {
            partitions = partitionList.stream().map(Partition::getColumnCode).collect(Collectors.toList());
            insertSql.append(" PARTITION ( ");
            for (Partition metaColumn : partitionList) {
                insertSql.append(metaColumn.getColumnCode()).append(",");
            }
            insertSql.deleteCharAt(insertSql.length() - 1);
            insertSql.append(") ");
        }
        insertSql.append(Constants.NEW_LINE_STR);
        // hive表字段顺序
        if (CollUtil.isNotEmpty(physicColumnList)) {
            List<String> selectColumnCodes = columnList.stream().map(Column::getColumnCode)
                    .collect(Collectors.toList());
            List<String> columnCodes = new ArrayList<>();
            for (Column column : physicColumnList) {
                if (CollUtil.isNotEmpty(partitionList) && partitions.contains(column.getColumnCode())) {
                    continue;
                }
                if (selectColumnCodes.contains(column.getColumnCode())) {
                    columnCodes.add(column.getColumnCode());
                } else {
                    // 数据库有，查询无，设置空值
                    columnCodes.add("null");
                }
            }
            // 动态分区，分区字段放在最后
            if (CollUtil.isNotEmpty(partitions)) {
                for (Partition metaColumn : partitionList) {
                    columnCodes.add(metaColumn.getColumnCode());
                }
            }
            insertSql.append("SELECT ").append(Constants.NEW_LINE_STR);
            insertSql.append(StringUtils.join(columnCodes, ",\n"));
            insertSql.append(Constants.NEW_LINE_STR).append(" FROM ");
            if (StringUtils.isNotEmpty(tempTableCode)) {
                insertSql.append(schemaCode).append(".").append(tempTableCode).append(";");
            } else {
                insertSql.append(" (").append(selectSql).append(") t ;");
            }
        } else {
            if (StringUtils.isNotEmpty(tempTableCode)) {
                insertSql.append("SELECT ").append(Constants.NEW_LINE_STR);
                List<String> columnCodes = new ArrayList<>();
                for (Column column : columnList) {
                    if (CollUtil.isNotEmpty(partitionList) && partitions.contains(column.getColumnCode())) {
                        continue;
                    }
                    columnCodes.add(column.getColumnCode());
                }
                // 动态分区，分区字段放在最后
                if (CollUtil.isNotEmpty(partitions)) {
                    for (Partition metaColumn : partitionList) {
                        columnCodes.add(metaColumn.getColumnCode());
                    }
                }
                insertSql.append(StringUtils.join(columnCodes, ",\n"));
                insertSql.append(Constants.NEW_LINE_STR).append(" FROM ").append(schemaCode).append(".")
                        .append(tempTableCode).append(";");
            } else {
                insertSql.append(" ").append(selectSql).append(";");
            }
        }
        return insertSql.toString();
    }

    @Override
    public String getDropTableSql(String schemaCode, String tableCode) {
        StringBuilder builder = new StringBuilder("DROP TABLE  IF EXISTS ");
        if (StringUtils.isNotBlank(schemaCode)) {
            builder.append(schemaCode).append(".");
        }
        builder.append(tableCode).append(" PURGE;").append(Constants.NEW_LINE_STR);
        return builder.toString();
    }

    /**
     * Hive不支持有注释 非必要删除，前置调度SQL标识符SKIP_ERROR，当前行SQL执行出错也会继续往下执行 不能保证表存在时可以调用当前方法，否则调用getDropTableSql方法
     *
     * @param schemaCode 库编码
     * @param tableCode  表编码
     * @return drop table语句
     */
    @Override
    public String getDropTableSqlNoNecessary(String schemaCode, String tableCode) {
        return getDropTableSql(schemaCode, tableCode);
    }

    /**
     * hive不支持删除delete
     */
    @Override
    public String deleteSql(String schemaCode, String tableCode, Map<String, String> whereMap) {
        StringBuilder deleteSql = new StringBuilder();
        deleteSql.append("TRUNCATE TABLE ").append(schemaCode).append(".").append(tableCode);
        deleteSql.append(";").append(Constants.NEW_LINE_STR);
        return deleteSql.toString();
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_HIVE;
    }

    @Override
    public String getDefaultColumnType() {
        return KeyValues.HIVE_DATA_TYPE_BIGINT;
    }

    @Override
    public String getStringColumnType() {
        return KeyValues.HIVE_DATA_TYPE_STRING;
    }

    @Override
    protected ColumnType getColumnType(String columnType) {
        return null;
    }

    @Override
    public void getPage(StringBuilder sql, Integer pageIndex, Integer pageSize) {
        if (null != pageSize && pageSize > 0) {
            sql.append(SqlUtils.SQL_LIMIT).append(pageSize);
        }
    }

    @Override
    public String getIfNull(Column column) {
        StringBuffer outField = new StringBuffer();
        outField.append(" if(").append(column.getColumnCode()).append(" is null,").append("0,")
                .append(column.getColumnCode()).append(") ");
        if (column.getColumnAccuracy() != null && column.getColumnAccuracy() != 0) {
            outField.insert(0, " round(");
            outField.append(",").append(column.getColumnAccuracy()).append(") ");
        }
        return outField.toString();
    }
}
