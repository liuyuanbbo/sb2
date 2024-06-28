package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class TimeDimension implements Serializable {

    /**
     * 小时字段
     */
    private String hourFieldColumnCode;

    /**
     * 日期字段
     */
    private String dayFieldColumnCode;

    /**
     * 月份字段
     */
    private String monthFieldColumnCode;

    /**
     * 年份字段
     */
    private String yearFieldColumnCode;
}