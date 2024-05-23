package org.zmz.b.infra.repository.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_data_center_calculate")
public class Calculate {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("rule_id")
    private Long ruleId;
    @TableField("en_name")
    private String enName;
    private String parameters;
    private String func;
    private String expression;
}
