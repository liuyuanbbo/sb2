package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataopen.ScheduleInfo;
import org.zmz.c.pojo.dataopen.ScheduleSplit;

import java.util.List;

@Getter
@Setter
public class ScheduleInfoQo extends ScheduleInfo {

    private List<ScheduleSplit> splitList;

}