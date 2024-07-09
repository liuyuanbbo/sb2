package org.zmz.c.service.dataopen.sql;

import lombok.extern.slf4j.Slf4j;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.utils.JsonUtil;
import org.zmz.c.utils.KeyValues;

import java.util.Map;

@Slf4j
public class ObjectWhaleHouseSqlParser extends ObjectClickHouseSqlParser {

    private static ObjectWhaleHouseSqlParser instance;

    public static ObjectWhaleHouseSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjectWhaleHouseSqlParser();
        }
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_WHALEHOUSE;
    }

    /**
     * 删除whalehouse数据
     *
     * @param schemaCode 库编码
     * @param tableCode  表编码
     * @param whereMap   过滤条件
     * @return String
     */
    @Override
    public String deleteSql(String schemaCode, String tableCode, Map<String, String> whereMap) {

        log.info("WhaleHouse不支持 DELETE WHERE删除语句,只能清表,入参是,whereMap={}", JsonUtil.toJson(whereMap));

        return "TRUNCATE TABLE " + schemaCode + "." + tableCode +
                ";" + Constants.NEW_LINE_STR;
    }

    /**
     * whalehouse支持创建create as 语法不友好，改成创建视图的方式
     *
     * @param schemaCode 库
     * @param tableCode  表
     * @param selectSql  查询语句
     */
    @Override
    public String getCreateAsSql(String schemaCode, String tableCode, String selectSql) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE VIEW ").append(schemaCode).append(".").append(tableCode);
        builder.append(" AS ");
        builder.append(Constants.NEW_LINE_STR).append(selectSql).append(";");
        return builder.toString();
    }

}