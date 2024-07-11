package org.zmz.c.utils;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DealConditionParamUtils {

    public static Set<Long> getTableIdsByMap(Map<String, Map<String, String>> aliasMap, Map<Long, ?> map) {
        Set<Long> ids = new HashSet<>();
        for (Map.Entry<String, Map<String, String>> entry : aliasMap.entrySet()) {
            String tableId = entry.getValue().get("srcTableId");
            if (StringUtils.isEmpty(tableId)) {
                tableId = entry.getKey();
            }
            if (StringUtils.isNotEmpty(tableId) && null != map.get(Long.valueOf(tableId))) {
                ids.add(Long.valueOf(tableId));
            }
        }
        return ids;
    }

    public static void splicingConditionParam(List<String> list, StringBuilder sql) {
        if (CollUtil.isNotEmpty(list)) {
            if (BuildSqlUtil.sbIsNotEmpty(sql)) {
                sql.append(SqlUtils.SQL_AND);
            }
            sql.append(SqlUtils.STR_LEFT_BRACKET);
            sql.append(String.join(SqlUtils.SQL_AND, list));
            sql.append(SqlUtils.STR_RIGHT_BRACKET);
        }
    }
}