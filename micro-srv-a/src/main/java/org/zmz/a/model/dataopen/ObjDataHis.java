package org.zmz.a.model.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ObjDataHis {
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
    public LocalDateTime historyCreateTime;
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