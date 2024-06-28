package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "schedule_split")
@NameStyle(value = Style.camelhumpAndLowercase)
public class ScheduleSplit {

    public Long splitId;

    public Long scheduleId;
    public String splitKey;

    public String splitType;

    public String splitValue;

    public String splitIncrement;
}