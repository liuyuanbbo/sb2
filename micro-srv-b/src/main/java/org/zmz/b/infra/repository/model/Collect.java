package org.zmz.b.infra.repository.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_data_center_collect")
public class Collect extends BaseModel {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("rule_id")
    private Long ruleId;
    @TableField("en_name")
    private String enName;
    @TableField("data_source")
    private String dataSource;
    @TableField("collect_way")
    private Integer collectWay;
    @TableField("content")
    private String content;
    @TableField("duplicate_flag")
    private Integer duplicateFlag;
    @TableField("period_format")
    private String periodFormat;
}
