package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.utils.SqlUtils;

import java.util.List;
import java.util.Map;

public final class SqlBuilderHelper {

    private SqlBuilderHelper() {
    }

    /**
     * 替换引号
     *
     * @param srcStr 账期表达式
     * @param isStr  是否字符串
     * @return 返回替换后的表达式
     */
    public static String replaceString(String srcStr, boolean isStr) {
        if (!isStr) {
            return srcStr.replaceAll("'", "");
        }
        return srcStr;
    }

    public static String addQuot(String srcStr, boolean isStr) {
        if (isStr) {
            return "'" + srcStr + "'";
        }
        return srcStr;
    }

    public static void copyCommonAlias(Map<String, String> tableAlias, List<MetricsDimensionPathVo> pathVos,
                                       Map<String, String> publicAlias) {
        if (MapUtil.isEmpty(tableAlias) || CollectionUtils.isEmpty(pathVos)) {
            return;
        }
        MetricsDimensionPathVo first = pathVos.stream().findFirst().orElse(null);
        if (StringUtils.isNoneBlank(tableAlias.get(String.valueOf(first.getSrcTableId())))) {
            publicAlias.put(String.valueOf(first.getSrcTableId()),
                    tableAlias.get(String.valueOf(first.getSrcTableId())));
        }

        for (MetricsDimensionPathVo pathVo : pathVos) {
            if ("1".equals(pathVo.getMetricsInnerPath())) {
                if (null != tableAlias.get(String.valueOf(pathVo.getSrcTableId()))) {
                    publicAlias.put(String.valueOf(pathVo.getSrcTableId()),
                            tableAlias.get(String.valueOf(pathVo.getSrcTableId())));
                }
                if (null != tableAlias.get(String.valueOf(pathVo.getTgtTableId()))) {
                    publicAlias.put(String.valueOf(pathVo.getTgtTableId()),
                            tableAlias.get(String.valueOf(pathVo.getTgtTableId())));
                }
            }
        }
    }

    public static void copyPublicToPathAlias(Map<String, String> publicAlias, Map<String, Map<String, String>> alias) {
        for (Map.Entry<String, Map<String, String>> mapEntry : alias.entrySet()) {
            mapEntry.getValue().putAll(publicAlias);
        }
    }

    /**
     * 检查度量路径上是否有组织维表
     *
     * @param dataPrivCtrlInfo 权限参数
     * @param pathsMap         度量路径
     * @return true/有,false/没有
     */
    public static boolean hasOrgTable(DataPrivCtrlVo dataPrivCtrlInfo,
                                      Map<String, List<MetricsDimensionPathVo>> pathsMap) {
        ModelInfo modelInfo = dataPrivCtrlInfo.getOrgDimensionModelInfo();
        if (null == modelInfo || CollectionUtils.isEmpty(pathsMap)) {
            return false;
        }
        Long metaDataId = modelInfo.getMetaDataInfo().getMetaDataId();
        for (Map.Entry<String, List<MetricsDimensionPathVo>> entry : pathsMap.entrySet()) {
            for (MetricsDimensionPathVo path : entry.getValue()) {
                if ((null != path.getSrcTableId() && path.getSrcTableId().equals(metaDataId))) {
                    return true;
                }
                if ((null != path.getTgtTableId() && path.getTgtTableId().equals(metaDataId))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasOrgTable(DataPrivCtrlVo dataPrivCtrlInfo, List<MetricsDimensionPathVo> paths) {
        ModelInfo modelInfo = dataPrivCtrlInfo.getOrgDimensionModelInfo();
        if (null == modelInfo || CollectionUtils.isEmpty(paths)) {
            return false;
        }
        for (MetricsDimensionPathVo path : paths) {
            if (path.getSrcTableId().equals(modelInfo.getMetaDataInfo().getMetaDataId())
                    || path.getTgtTableId().equals(modelInfo.getMetaDataInfo().getMetaDataId())) {
                return true;
            }
        }
        return false;
    }

    public static String getAliasName(Map<String, Map<String, String>> alias, Long srcTableId) {
        for (Map.Entry<String, Map<String, String>> mapEntry : alias.entrySet()) {
            if (StringUtils.isNotBlank(mapEntry.getValue().get(String.valueOf(srcTableId)))) {
                return mapEntry.getValue().get(String.valueOf(srcTableId));
            }
        }
        return null;
    }

    public static String getAliasName(Map<String, Map<String, String>> alias, Long srcTableId, String path) {
        for (Map.Entry<String, Map<String, String>> mapEntry : alias.entrySet()) {
            if (StringUtils.isNotEmpty(path) && mapEntry.getKey().endsWith("," + path)) {
                if (StringUtils.isNotBlank(mapEntry.getValue().get(String.valueOf(srcTableId)))) {
                    return mapEntry.getValue().get(String.valueOf(srcTableId));
                }
            }
        }
        return getAliasName(alias, srcTableId);
    }

    public static String fieldNotes(String columnName) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNoneBlank(columnName)) {
            sb.append("/**").append(columnName).append("*/");
        }
        return sb.toString();
    }

    public static DatasetColumnQo getOrgDimensionMinColumn(DataPrivCtrlVo dataPrivCtrlInfo) {
        if (checkDataPriv(dataPrivCtrlInfo)) {
            if (dataPrivCtrlInfo.getExistOrgDimensionColumn() == null) {
                return null;
            }
            if (dataPrivCtrlInfo.getAddOrgDimensionColumn() == null) {
                return dataPrivCtrlInfo.getExistOrgDimensionColumn();
            } else {
                return dataPrivCtrlInfo.getAddOrgDimensionColumn();
            }
        }
        return null;
    }

    public static boolean checkDataPriv(DataPrivCtrlVo dataPrivCtrlInfo) {
        if (null == dataPrivCtrlInfo) {
            return false;
        }
        return dataPrivCtrlInfo.isDataPrivCtrl();
    }

    public static String getColumnGroup(List<DatasetColumnQo> columnGroup, Map<String, StringBuilder> expMap,
                                        String tbName) {
        return getColumnGroup(columnGroup, expMap, tbName, false);
    }

    public static String getColumnGroup(List<DatasetColumnQo> columnGroup, Map<String, StringBuilder> expMap,
                                        String tbName, boolean singleSql) {
        StringBuilder groupSql = new StringBuilder();
        for (DatasetColumnQo grpCol : columnGroup) {
            String condValue = grpCol.getCondValue();
            String condType = grpCol.getCondType();
            String colCode = grpCol.getColumnCode();
            // 连接符，括号，算术运算符
            if ("connectOpt".equals(condType) || "bracket".equals(condType) || "arithmeticOpt".equals(condType)) {
                if ("arithmeticOpt".equals(condType) && "&".equals(condValue)) {
                    condValue = "and";
                } else if ("arithmeticOpt".equals(condType) && "||".equals(condValue)) {
                    condValue = "or";
                }
                groupSql.append(" ").append(condValue).append(" ");
                // 算术运算项目
            } else if ("arithmeticCondItem".equals(condType)) {
                if (StringUtils.isNoneBlank(grpCol.getAlias())) {
                    // 计算字段引用其他度量的别名
                    if (null != expMap.get(grpCol.getAlias())) {
                        groupSql.append(singleSql ? "" : "SUM(").append(expMap.get(grpCol.getAlias()))
                                .append(singleSql ? "" : ")");
                    } else {
                        groupSql.append(singleSql ? "" : "SUM(").append(tbName).append(SqlUtils.STR_POINT)
                                .append(grpCol.getAlias()).append(singleSql ? "" : ")");
                    }
                } else {
                    groupSql.append(colCode).append(" ");
                    // 算术运算条件
                    // 前端多传一个字段标识是逻辑操作
                    if (StrUtil.isNotEmpty(condValue)) {
                        groupSql.append("=").append(condValue).append(" ");
                    }
                }
            } else {
                groupSql.append(condValue);
            }
        }
        return groupSql.toString();
    }


    public static boolean isStringType(String columnType) {
        columnType = columnType.toLowerCase();
        return columnType.contains("char") || columnType.contains("string") || columnType.contains("text")
                || columnType.contains("clob");
    }

    public static boolean isStringType(String dbType, String columnType) {
        String strType = Constants.DATA_TYPE_STRING.get(dbType);
        return isStringType(columnType) || strType.equalsIgnoreCase(columnType);
    }

}