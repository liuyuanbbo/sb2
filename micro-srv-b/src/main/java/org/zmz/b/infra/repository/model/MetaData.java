package org.zmz.b.infra.repository.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_data_center_metadata")
public class MetaData extends BaseModel {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("rule_id")
    private Long ruleId;
    @TableField("en_name")
    private String enName;
    @TableField("zh_name")
    private String zhName;
    @TableField("default_show")
    private Integer defaultShow;
    @TableField("storage")
    private Integer storage;
    @TableField("dimension")
    private Integer dimension;
    @TableField("index_period")
    private Integer indexPeriod;
    @TableField("data_type")
    private String dataType;
    @TableField("source_type")
    private Integer sourceType;
    @TableField("life_cycle_id")
    private Integer lifeCycleId;
    @TableField("trace_id")
    private String traceId;
    @TableField("data_security")
    private Integer dataSecurity;
    @TableField("extra_width")
    private Integer extraWidth;
    @TableField("description")
    private String description;
    @TableField("index_sort")
    private Integer indexSort;
    @TableField("base_value")
    private Integer baseValue;
}
