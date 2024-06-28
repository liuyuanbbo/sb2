package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "schedule_info")
@NameStyle(value = Style.camelhumpAndLowercase)
public class ScheduleInfo {

    public Long scheduleId;

    public Integer scheduleGroup;

    public String objectType;

    public Long objectId;

    public String objectCode;

    public String scheduleName;

    public String scheduleType;

    public String scheduleData;

    public String comments;

    public String cycleType;

    public String cycleValue;

    public String srcSchemaCode;

    public String destSchemaCode;

    public String srcTable;

    public String destTable;

    public String scheduleExpress;

    public String timeRule;

    public Integer apiTaskState;

    public Long apiTaskId;

    public Integer state;

    public Long creator;

    public Date createDate;

    public Date startDate;

    public Date endDate;

    public Long planId;

    public Date nextRunTime;

    public String srcDatasource;

    public String tarDatasource;

    public Long tarDatasourceId;

    public String startAcct;

    public Long jobId;

    public String appPriority;

    public Date appOutputtime;

    public String isSsh;

    public String isSplit;

    /**
     * 关联任务标识jobId
     */
    private Long relJobId;

    /**
     * 是否预删除所有数据
     */
    private String preDel;
}