package org.zmz.c.pojo.dataopen;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import org.zmz.c.qo.dataopen.ProRestrictColumnVo;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
@Table(name = "PRO_RESTRICT")
public class ProRestrict {

    @Id
    @TableId
    public Long restrictId;

    /**
     * 限定名称
     */
    public String restrictName;

    /**
     * 限定编码
     */
    public String restrictCode;

    /**
     * 关联业务过程表标识
     */
    public Long proTableId;

    /**
     * 关联业务过程表编码
     */
    public String proTableCode;

    /**
     * 条件对象
     */
    public String columnExpress;

    /**
     * 限定逻辑sql
     */
    public String calculateLogicSql;

    /**
     * 状态 上架：1000 下架：1100 1200 删除
     */
    public String state;

    public Date createDate;

    public Long createStaff;

    public Long comAcctId;

    public Long objId;

    public String restrictType;

    public String version;

    public String comments;

    /**
     * 单一业务限定选择的字段标识
     */
    public Long proColumnId;

    /**
     * 项目ID
     */
    public Long projectId;

    public List<ProRestrictColumnVo> proRestrictColumnList;

    public String isSimilarCheck;

    /**
     * 是否动态参数字段1是0否
     */
    private String isDynamic;
}