package org.zmz.c.service.dataopen.sqltype;

import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
import org.zmz.c.utils.SqlUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static Map<String, String> getAliasMap(DatasetColumnQo metric, DatasetColumnQo dimension,
                                                  Map<String, Map<String, String>> aliasMap, boolean judgePath) {
        Map<String, String> map = new HashMap<>();
        if (MapUtil.isEmpty(aliasMap)) {
            return map;
        }
        if (!judgePath) {
            return aliasMap.get(Constants.OBJ_TREE_RELA_TYPE_V.equalsIgnoreCase(dimension.getRelaType())
                    ? metric.getTableId().toString()
                    : dimension.getTableId().toString());
        }
        String dimPath;
        // 虚拟对象
        if (Constants.OBJ_TREE_RELA_TYPE_V.equalsIgnoreCase(dimension.getRelaType())
                || metric.getTableId().equals(dimension.getTableId())) {
            dimPath = metric.getPath();
        } else {
            dimPath = getConvertDimPath(metric, dimension);
        }
        for (Map.Entry<String, Map<String, String>> entry : aliasMap.entrySet()) {
            String entryKey = entry.getKey();
            if (entryKey.startsWith(dimPath)
                    // 虚拟对象
                    && (Constants.OBJ_TREE_RELA_TYPE_V.equalsIgnoreCase(dimension.getRelaType())
                    || StringUtils.isNotBlank(entry.getValue().get(String.valueOf(dimension.getTableId()))))) {
                return entry.getValue();
            }
        }
        return map;
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
        if (dataPrivCtrlInfo == null) {
            return false;
        }
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

    /**
     * 从度量路径里面获取别名
     *
     * @param period    账期字段
     * @param dimension 维度
     * @param alias     路径别名集合
     * @return 别别名
     */
    public static String getAliasFromMetricPath(Column period, DatasetColumnQo dimension,
                                                Map<String, Map<String, String>> alias) {
        if (!CollectionUtils.isEmpty(alias)) {
            for (Map.Entry<String, Map<String, String>> entry : alias.entrySet()) {
                String tableAlias = null;
                if (null != period) {
                    tableAlias = entry.getValue().get(String.valueOf(period.getMetaDataId()));

                } else {
                    tableAlias = entry.getValue().get(String.valueOf(dimension.getTableId()));
                }
                if (StringUtils.isNotBlank(tableAlias)) {
                    return tableAlias;
                }
            }
        }
        return null;
    }

    public static String getAliasFromMetricOrTemp(DatasetColumnQo metric, Map<String, Map<String, String>> aliasMap,
                                                  Map<String, String> temTableAlias, DatasetColumnQo dimension) {
        String tbName = null;
        String path;
        if (metric.getTableId().equals(dimension.getTableId())) {
            path = metric.getPath();
        } else {
            path = getConvertDimPath(metric, dimension);
        }
        if (MapUtil.isNotEmpty(aliasMap.get(path))) {
            tbName = aliasMap.get(path).get(String.valueOf(dimension.getTableId()));
        } else {
            for (Map.Entry<String, Map<String, String>> entry : aliasMap.entrySet()) {
                if (entry.getKey().startsWith(metric.getPath())) {
                    Map<String, String> value = entry.getValue();
                    // 如果是账期字段，当作度量表字段处理
                    if (Constants.YES_VALUE_1.equals(dimension.getIsAcct())) {
                        tbName = value.get(String.valueOf(metric.getTableId()));
                    } else {
                        tbName = value.get(String.valueOf(dimension.getTableId()));
                    }
                }
                if (null != tbName) {
                    break;
                }
            }
        }
        if (StringUtils.isBlank(tbName)) {
            for (Map.Entry<String, String> entry : temTableAlias.entrySet()) {
                if (entry.getKey().startsWith(path)) {
                    tbName = entry.getValue();
                }
            }
        }
        return tbName;
    }

    public static String getAliasFromTempTableAndMetric(DatasetColumnQo metric, DatasetColumnQo dimension,
                                                        Map<String, List<MetricsDimensionPathVo>> metricsMap, Map<String, Map<String, String>> alias,
                                                        Map<String, String> temTableAlias) {
        // 从度量里面找
        String dimPath;
        if (metric.getTableId().equals(dimension.getTableId())) {
            dimPath = metric.getPath();
        } else {
            dimPath = getConvertDimPath(metric, dimension);
        }
        String tabAlias;
        for (Map.Entry<String, List<MetricsDimensionPathVo>> entryMetric : metricsMap.entrySet()) {
            if (StringUtils.isNotBlank(dimPath) && entryMetric.getKey().startsWith(dimPath)) {
                Map<String, String> map = alias.get(entryMetric.getKey());
                for (MetricsDimensionPathVo pathVo : entryMetric.getValue()) {
                    if (!ObjectUtils.isEmpty(pathVo.getSrcTableId())
                            && dimension.getTableId().equals(pathVo.getSrcTableId())) {
                        tabAlias = map.get(String.valueOf(dimension.getTableId()));
                        if (StringUtils.isNotEmpty(tabAlias)) {
                            return tabAlias;
                        }
                    }
                    if (!ObjectUtils.isEmpty(pathVo.getTgtTableId())
                            && dimension.getTableId().equals(pathVo.getTgtTableId())) {
                        tabAlias = map.get(String.valueOf(dimension.getTableId()));
                        if (StringUtils.isNotEmpty(tabAlias)) {
                            return tabAlias;
                        }
                    }
                }
            }
        }

        // 从临时表里面找
        if (dimPath != null) {
            for (Map.Entry<String, String> entry : temTableAlias.entrySet()) {
                if (entry.getKey().startsWith(dimPath)) {
                    return entry.getValue();
                }
            }
        } else {
            return MapUtil.getStr(alias.get(dimension.getTableId().toString()), dimension.getTableId().toString());
        }
        return null;
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
            if (ObjectUtils.isEmpty(dataPrivCtrlInfo.getExistOrgDimensionColumn())) {
                return null;
            }
            if (ObjectUtils.isEmpty(dataPrivCtrlInfo.getAddOrgDimensionColumn())) {
                return dataPrivCtrlInfo.getExistOrgDimensionColumn();
            } else {
                return dataPrivCtrlInfo.getAddOrgDimensionColumn();
            }
        }
        return null;
    }

    // 检查数据权限
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
                    if (StringUtils.isNotEmpty(condValue)) {
                        groupSql.append("=").append(condValue).append(" ");
                    }
                }
            } else {
                groupSql.append(condValue);
            }
        }
        return groupSql.toString();
    }

    public static boolean isStringType(String dbType, String columnType) {
        String strType = Constants.DATA_TYPE_STRING.get(dbType);
        return isStringType(columnType) || strType.equalsIgnoreCase(columnType);
    }

    /**
     * 增加Date,date类型一般情况下要加引号
     */
    public static boolean isStringType(String columnType) {
        columnType = columnType.toLowerCase();
        return columnType.contains("char") || columnType.contains("string") || columnType.contains("text")
                || columnType.contains("clob") || columnType.contains("date");
    }

    public static String getConvertDimPath(DatasetColumnQo metric, DatasetColumnQo dimension) {
        String path = null;
        if (Constants.OBJ_TREE_RELA_TYPE_N.equals(metric.getRelaType())) {
            // n端度量
            if (metric.getRelaType().equals(dimension.getRelaType())) {
                // 度量和维度同一对象
                if (metric.getTableId().equals(dimension.getTableId())) {
                    path = metric.getPath().startsWith("n,") ? metric.getPath()
                            : metric.getRelaType() + "," + metric.getTableId() + "," + metric.getPath();
                } else {
                    path = metric.getPath().startsWith("n,") ? metric.getPath() + "," + dimension.getTableId()
                            : metric.getRelaType() + "," + metric.getTableId() + "," + metric.getPath() + ","
                            + dimension.getTableId();
                }
            } else {
                // 维度出自于当前对象本身或者1端
                path = metric.getPath().startsWith("n,") ? metric.getPath() + "," + dimension.getPath()
                        : metric.getRelaType() + "," + metric.getTableId() + "," + metric.getPath() + ","
                        + dimension.getPath();
            }
        } else if (Constants.OBJ_TREE_RELA_TYPE_2.equals(metric.getRelaType())) {
            // 多维
            if (metric.getRelaType().equals(dimension.getRelaType())) {
                // 多维指标与维度同一对象
                if (metric.getTableId().equals(dimension.getTableId())) {
                    path = "-2," + metric.getTableId();
                } else {
                    path = "-2," + metric.getTableId() + "," + dimension.getTableId();
                }
            } else {
                // 维度出自于当前对象本身或者1端
                path = "-2," + metric.getTableId() + "," + dimension.getPath();
            }
        } else if (Constants.OBJ_TREE_RELA_TYPE_1.equals(metric.getRelaType())) {
            // 主分析对象的1端
            if (metric.getRelaType().equals(dimension.getRelaType())
                    && (metric.getIsOrgDimension() == null || metric.getIsOrgDimension() != 1
                    || dimension.getIsOrgDimension() == null || dimension.getIsOrgDimension() != 1)) {
                // 维度与1端度量同一对象
                if (metric.getTableId().equals(dimension.getTableId())) {
                    path = "-1," + metric.getTableId() + "," + dimension.getPath();
                } else {
                    path = "-1," + metric.getTableId() + "," + dimension.getTableId() + "," + dimension.getPath();
                }
            } else {
                path = "-1," + metric.getTableId() + "," + dimension.getPath();
            }
        } else if (Constants.OBJ_TREE_RELA_TYPE_0.equals(metric.getRelaType())) {
            // 主分析对象
            if (metric.getRelaType().equals(dimension.getRelaType())) {
                if (metric.getTableId().equals(dimension.getTableId())) {
                    path = "-0," + metric.getTableId();
                } else {
                    path = "-0," + metric.getTableId() + "," + dimension.getTableId();
                }
            } else {
                path = "-0," + metric.getTableId() + "," + dimension.getPath();
            }
        }
        return path;
    }

    public static String getMetricPath(DatasetColumnQo metric) {
        String path = null;
        if (Constants.OBJ_TREE_RELA_TYPE_N.equals(metric.getRelaType())) {
            if (!metric.getPath().startsWith("n")) {
                path = metric.getRelaType() + "," + metric.getTableId() + "," + metric.getPath();
            } else {
                path = metric.getPath();
            }
        } else if (Constants.OBJ_TREE_RELA_TYPE_2.equals(metric.getRelaType())) {
            path = "-2," + metric.getTableId();
        } else if (Constants.OBJ_TREE_RELA_TYPE_1.equals(metric.getRelaType())) {
            path = "-1," + metric.getTableId();
        } else if (Constants.OBJ_TREE_RELA_TYPE_0.equals(metric.getRelaType())) {
            path = "-0," + metric.getTableId();
        }
        return path;
    }

    /**
     * 是否有时间计算，同环比年月累计
     */
    public static boolean isGrowthOrTotal(String func) {
        SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(func);
        return isGrowthOrTotal(funcEnum);
    }

    public static boolean isGrowthOrTotal(SqlFuncEnum funcEnum) {
        Set<SqlFuncEnum> growthOrTotalFuncs = Set.of(
                SqlFuncEnum.yoy, SqlFuncEnum.yoyGrowth,
                SqlFuncEnum.pp, SqlFuncEnum.momGrowth,
                SqlFuncEnum.mm, SqlFuncEnum.mmGrowth,
                SqlFuncEnum.yearEnd, SqlFuncEnum.yearEndGrowth,
                SqlFuncEnum.monthTotal, SqlFuncEnum.yearTotal
        );
        return growthOrTotalFuncs.contains(funcEnum);
    }

    public static boolean isGrowth(SqlFuncEnum funcEnum) {
        Set<SqlFuncEnum> growthOrTotalFuncs = new HashSet<>(
                Arrays.asList(SqlFuncEnum.yoy, SqlFuncEnum.yoyGrowth, SqlFuncEnum.pp, SqlFuncEnum.momGrowth, SqlFuncEnum.mm,
                        SqlFuncEnum.mmGrowth, SqlFuncEnum.yearEnd, SqlFuncEnum.yearEndGrowth));
        return growthOrTotalFuncs.contains(funcEnum);
    }

    public static boolean isTotal(SqlFuncEnum funcEnum) {
        return SqlFuncEnum.monthTotal.equals(funcEnum) || SqlFuncEnum.yearTotal.equals(funcEnum);
    }

    public static boolean isTotal(String func) {
        SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(func);
        return isTotal(funcEnum);
    }
}
