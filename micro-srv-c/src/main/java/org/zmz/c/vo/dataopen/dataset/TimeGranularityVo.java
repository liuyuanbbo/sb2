package org.zmz.c.vo.dataopen.dataset;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.qo.dataopen.Column;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class TimeGranularityVo {
    /**
     * 表id
     **/
    private Long tableId;

    /**
     * 时间粒度
     */
    private String timeGranularity;

    /**
     * 时间粒度值--编码
     */
    private String timeGranularityValueColumn;

    /**
     * 时间粒度字段--字段
     */
    private String timeGranularityColumn;

    /**
     * 字段
     **/
    private Map<String, Column> columnMap = new HashMap<>(4);
}