package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.zmz.c.mapper.dataopen.ObjInfoMapper;
import org.zmz.c.pojo.dataopen.ObjInfo;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ReplaceParamQo;
import org.zmz.c.service.dataopen.remote.DataCommonService;
import org.zmz.c.utils.AcctTimeUtil;
import org.zmz.c.utils.JsonUtil;
import org.zmz.c.utils.SqlUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AcctSqlService {

    @Resource
    DataCommonService dataCommonService;

    @Resource
    ObjInfoMapper objInfoMapper;

    /**
     * 预览账期周期
     */
    public String getDataCycle(Collection<ModelInfo> modelInfoList) {
        // 按最细账期到最粗
        Set<String> dataCycles = modelInfoList.stream().map(modelInfo -> modelInfo.getMetaDataInfo().getDataCycleCode())
                .collect(Collectors.toSet());
        return AcctTimeUtil.getCycleType(dataCycles);
    }

    public String formatAcct(String sql, String cycleType) {
        return formatAcct(sql, cycleType, null);
    }

    public String formatAcct(String sql, String cycleType, String acct) {
        if (StringUtils.isEmpty(acct)) {
            acct = AcctTimeUtil.getDefAcctValue(cycleType);
        }
        // 调度替换复杂表达式
        ReplaceParamQo replaceParamQo = new ReplaceParamQo();
        replaceParamQo.setOriginalValue(sql);
        replaceParamQo.setAcct(acct);
        log.info("replaceParamQo: {}", JsonUtil.toJson(replaceParamQo));
        return sql;
    }

    /**
     * 是否需要关联时间维度表 TODO 支持小时分钟改造
     */
    public StringBuilder joinTimeDimension(DatasetColumnAndConditionQo params, StringBuilder sql) {
        // 判断是否需要关联时间维度表
        // joinAcct=true时，表示生成任务sql，无需关联时间维度表；仅预览时关联时间维度表
        if (Constants.SQL_PREVIEW.equals(params.getSqlMode()) && !sql.isEmpty()) {
            List<DatasetColumnQo> acctCols = params.getColumnList().stream().filter(col -> {
                return Constants.YES_VALUE_1.equals(col.getIsAcct())
                        && Constants.APP_TYPE_DIMENSION.equals(col.getAppType());
            }).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(acctCols)) {
                DatasetColumnQo acctCol = acctCols.stream()
                        .filter(col -> Constants.SCHEDULE_LOOP_TYPE_D.equals(col.getCycleType()))
                        .findFirst().orElse(acctCols.get(0));
                if (acctCol.getObjectId() == null) {
                    return sql;
                }

                String cycleType = acctCol.getCycleType();
                // 获取数据库类型，不同数据库类型的时间维表只有一张

                ObjInfo objInfo = objInfoMapper.selectById(acctCol.getObjectId());
                // 获取时间维表
                ModelInfo timeModel = dataCommonService.getTimeModel(objInfo.getTableType());
                if (timeModel == null) {
                    return sql;
                }
                StringBuilder timeSql = new StringBuilder();
                // 根据账期周期类型，决定时间维度表查询字段
                String acct = Constants.SCHEDULE_LOOP_TYPE_M.equals(cycleType)
                        ? timeModel.getBussinessAttr().getTimeDimension().getMonthFieldColumnCode()
                        : timeModel.getBussinessAttr().getTimeDimension().getDayFieldColumnCode();
                timeSql.append(SqlUtils.SQL_SELECT).append(" ").append(SqlUtils.STR_DISTINCT).append(" tm.")
                        .append(acct).append(SqlUtils.SQL_AS).append(acctCol.getAlias()).append(",");
                for (DatasetColumnQo columnQo : params.getColumnList()) {
                    if (Constants.YES_VALUE_1.equals(columnQo.getIsAcct())
                            && cycleType.equalsIgnoreCase(columnQo.getCycleType())) {
                        continue;
                    }
                    timeSql.append("t.").append(columnQo.getAlias()).append(",");
                }
                timeSql.deleteCharAt(timeSql.length() - 1);
                timeSql.append(SqlUtils.SQL_FROM).append(timeModel.getMetaDataInfo().getSchemaCode())
                        .append(SqlUtils.STR_POINT).append(timeModel.getMetaDataInfo().getMetaDataCode()).append(" tm ")
                        .append(SqlUtils.SQL_LEFT_JOIN).append(SqlUtils.STR_LEFT_BRACKET).append(sql)
                        .append(SqlUtils.STR_RIGHT_BRACKET).append("t ").append(SqlUtils.SQL_ON).append(" tm.").append(acct)
                        .append(SqlUtils.STR_EQUAL).append("t.").append(acctCol.getAlias());
                // 全局条件
                if (CollUtil.isNotEmpty(params.getCondList())) {
                    DatasetConditionQo acctCond = params.getCondList().stream().filter(cond -> {
                        return (Constants.YES_VALUE_1.equals(cond.getIsAcct())
                                && cycleType.equalsIgnoreCase(cond.getCycleType()));
                    }).findFirst().orElse(null);
                    if (acctCond != null && acctCond.getCondValue().contains("~")) {
                        // 分割后面的值
                        String[] splitArray = acctCond.getCondValue().split("~");
                        for (int i = 0; i < splitArray.length; i++) {
                            String str = splitArray[i];
                            splitArray[i] = "'" + str + "'";
                        }
                        String joinStr = StringUtils.join(" AND ", splitArray);
                        timeSql.append(SqlUtils.SQL_WHERE).append(" tm.").append(acct).append(SqlUtils.SQL_BETWEEN)
                                .append(joinStr);
                    }
                }
                return timeSql;
            }
        }
        return sql;
    }
}
