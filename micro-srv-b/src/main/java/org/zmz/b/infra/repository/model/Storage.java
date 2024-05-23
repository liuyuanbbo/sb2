package org.zmz.b.infra.repository.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_data_center_storage")
public class Storage {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("rule_id")
    private Long ruleId;
    @TableField("data_source")
    private String dataSource;
    @TableField("table_prefix")
    private String tablePrefix;
}
