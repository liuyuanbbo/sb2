package org.zmz.c.pojo.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@TableName(schema = "obj_info")
public class ObjInfo {
    @TableId
    public Long objectId;
    public Long parentId;
    public Long grpId;
    public String objectCode;
    public String objectName;
    public String primaryKey;
    public Long metaTableId;
    public String tableCode;
    /**
     * 表类型
     */
    public String tableType;
    /**
     * 状态, 1000提交 1100草稿 1200删除
     */
    public String statusCd;
    public String layer;
    public String domain;
    /**
     * 描述
     */
    public String comments;
    /**
     * factory 通过对象工厂生成; normal 通过对象管理生成；virtual
     */
    public String createType;
    /**
     * 创建时间
     */
    public LocalDate createDate;
    /**
     * 创建人ID
     */
    public Long operId;
    /**
     * 企业ID
     */
    public Long comAcctId;
    /**
     * 宽表对应的数据目录标识
     */
    public Long dirId;
    /**
     * 数据源ID
     */
    public Long datasourceId;
    /**
     * 对象统计类型，1清单2统计3维度
     */
    public String dataCntType;
    /**
     * 对象别名，数据集对象树维度目录名
     */
    public String objectAlias;
    /**
     * 项目ID
     */
    public Long projectId;
    /**
     * 维度来源属性
     */
    private String keyCode;
    /**
     * 工单号
     */
    private String orderNo;
}