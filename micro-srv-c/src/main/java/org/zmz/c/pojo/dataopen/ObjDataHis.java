package org.zmz.c.pojo.dataopen;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
@Table(name = "obj_data_his")
public class ObjDataHis {

    @TableId
    @Id
    public Long versionId;

    /**
     * 数据类型
     */
    public String dataType;

    /**
     * 数据标识
     */
    public Long dataId;

    /**
     * 版本
     */
    public String version;

    /**
     * 创建时间
     */
    public Date historyCreateTime;

    /**
     * 创建人
     */
    public Long createStaff;

    /**
     * 创建人
     */
    public String createStaffName;

    /**
     * 数据内容
     */
    public String json;

}