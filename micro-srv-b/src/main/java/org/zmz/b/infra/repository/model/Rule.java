package org.zmz.b.infra.repository.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_data_center_rule")
public class Rule {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("business_id")
    private String businessId;
    @TableField("business_name")
    private String businessName;
    @TableField("en_name")
    private String enName;
    @TableField("rule_name")
    private String ruleName;
    @TableField("rule_type")
    private String ruleType;
    @TableField("version")
    private String version;
}
