package org.zmz.b.infra.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseModel implements Serializable {
    @TableField("gmt_create")
    private LocalDateTime createDate;
    @TableField("gmt_modified")
    private LocalDateTime updateDate;
    private Integer status = 1;
}
