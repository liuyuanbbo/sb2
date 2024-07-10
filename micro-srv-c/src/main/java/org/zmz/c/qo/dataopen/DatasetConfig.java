package org.zmz.c.qo.dataopen;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class DatasetConfig implements Serializable {

    /**
     * 主分析对象
     */
    private Long objectId;

    /**
     * 数据集标识，查看详情时，预览数据从目标表查询
     */
    private Long appId;

    /**
     * 数据集标识，app 数据编排（v6）, index 指标数据集，consume 数据消费数据集；
     */
    private String appType;

    /**
     * 数据集标识，查看详情时，预览数据从目标表查询
     */
    private Long analysisId;

    /**
     * 库表类型
     */
    private String tableType;

    /*********************** 分割线，以上为配置信息，以下为sql/数据预览参数 ************************/
    /**
     * sql模式：preview:预览sql; task:任务sql; check: 校验sql
     */
    private String sqlMode = "preview";

    /**
     * 用户组织，一次性统计，需要用组织占位符${orgId}
     */
    private String orgIdValue;

    /**
     * 调度周期类型，同scheduleInfo里的cycleType 一次性任务时，全局账期条件不处理； 周期性任务时，账期字段作为维度了，则需要处理全局账期条件，范围内拆分为等于；未选择账期字段作为维度逻辑不变
     */
    private String scheduleType;

    /**
     * 是否拼接时间维表
     */
    private boolean joinTimeDimension = false;

    /**
     * 预览账期
     */
    public String acct;

    /**
     * 预览起始页
     */
    private Integer pageIndex;

    /**
     * 预览记录数
     */
    private Integer pageSize;

    /**
     * 创建人(审批成功注册任务)
     */
    public Long creator;

    /**
     * 账期偏移配置
     */
    private AcctOffsetConf acctOffset;

    /*********************** 分割线，以上为sql/数据预览信息，以下为悬浮树参数 ************************/
    /**
     * 悬浮树，增加拖拽逻辑 【对象目录层级优化】 以N端对象为中心，加载自己对象上的属性及1端对象的属性树
     */
    private Long rootObjectId;

    /**
     * 【对象目录层级优化】 以N端对象为中心，加载自己对象上的属性及1端对象的属性树(n/2)
     */
    private String rootRelaType;

    /**
     * 1离线取数 2在线取数
     */
    private String accessType;

    public boolean isAcctOffset() {
        return this.acctOffset != null &&
                CollUtil.isNotEmpty(this.acctOffset.getLeftTableIds()) &&
                CollUtil.isNotEmpty(this.acctOffset.getRightTableIds());
    }

    /**
     * 输出模式,默认为table输出模式,或者sql
     */
    protected String outPutMode = "table";
}