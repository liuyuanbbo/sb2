package org.zmz.c.pojo.dataopen;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

@Getter
@Setter
@ToString
@Table(name = "dim_index_info")
@NameStyle(value = Style.camelhumpAndLowercase)
public class DimIndexInfo {
    @TableId
    @Id
    private Long dimIndexId;
    /**
     * 指标编码
     */
    private String indexCode;

    /**
     * 指标名列
     */
    private String dimIndexName;

    /**
     * 宽表的metaTableId
     */
    private Long metaTableId;

    /**
     * 指标来源
     */
    private String srcType;

    /**
     * 指标表编码
     */
    private String tableCode;

    /**
     * 指标字段编码
     */
    private String fieldCode;

    /**
     * 指标字段在宽表中的序列号
     */
    private Integer indexWideColSeq;

    /**
     * 指标目录标识
     */
    private Long catId;

    /**
     * 指标周期
     */
    private String indexCycle;

    /**
     * 指标状态
     */
    private String state;

    /**
     * 指标业务口径
     */
    private String dimIndexBusiDesc;

    /**
     * 指标技术口径
     */
    private String dimIndexTechDesc;

    /**
     * 指标描述
     */
    private String dimIndexDesc;

    /**
     * 指标创建时间
     */
    private Date createDate;

    /**
     * 指标创建人
     */
    private Long createStaff;

    /**
     * 指标修改时间
     */
    private Date indexModifyDate;

    /**
     * 通用系统企业ID
     */
    private Long comAcctId;

    /**
     * 关联工单号
     */
    private String orderNo;

    /**
     * 来源系统,集团标签group
     */
    private String srcSystem;
}
