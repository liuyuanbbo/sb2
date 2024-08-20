package org.zmz.d.pojo.dev154.dataopen;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
@TableName(value = "pro_index")
public class ProIndex {
    /**
     * 原子指标标识
     */
    @TableId
    public Long indexId;

    /**
     * 指标名称
     */
    public String indexName;

    /**
     * 指标编码
     */
    public String indexCode;

    /**
     * 数据类型
     */
    public String dataType;

    /**
     * 关联业务过程表标识
     */
    public Long proTableId;

    /**
     * 业务过程表编码
     */
    public String proTableCode;

    /**
     * 关联业务过程表字段ID
     */
    public Long proColumnId;

    /**
     * 关联字段编码
     */
    public String columnCode;

    /**
     * 指标类型
     */
    public String indexType;

    /**
     * 是否去重 0否 1是
     */
    public Integer isNoRepeat;

    /**
     * 计算类型
     */
    public String calculateType;

    /**
     * 计算逻辑sql
     */
    public String calculateLogicSql;

    /**
     * 状态 1000 上架；1100 下架；1200 删除
     */
    public String state;

    public Date createDate;

    public Long createStaff;

    /**
     * 企业标识
     */
    public Long comAcctId;

    /**
     * 对象id
     */
    public Long objectId;

    /**
     * 业务口径
     */
    public String comments;

    /**
     * 对象id
     */
    public Long grpId;

    /**
     * 技术口径
     */
    public String techDesc;

    /**
     * 所属组织
     */
    public Long orgId;

    /**
     * 需求人
     */
    public String indexSourceSys;

    /**
     * it建设人
     */
    public String indexInfMan;

    /**
     * 版本号
     */
    public String version;

    /**
     * 指标单位
     */
    public String indexUnit;

    /**
     * 指标类型
     */
    public String indexKindDesc;

    /**
     * 指标级别
     */
    public String indexLevelDesc;

    /**
     * 指标价值
     */
    public String indexValueDesc;

    /**
     * 涉及系统
     */
    public String indexSrcSystem;

    /**
     * 指标生命期
     */
    public String indexLifecycle;

    /**
     * 指标排序
     */
    public Integer seq;

    /**
     * 项目ID
     */
    public Long projectId;

    /**
     * 修改原子指标目录，是否同步关联的派生指标目录
     */
    public String synIndex;

    /**
     * 工单号
     */
    public String orderNo;

    public String indexSort;

    /**
     * 来源系统
     */
    private String srcSystem;
}