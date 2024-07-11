package org.zmz.c.pojo.dataportal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@TableName(value = "DC_SYSTEM_CONFIG_LIST")
@ToString
public class DcSystemConfigList implements Serializable {
    @TableId
    private Long id;

    private String standType;

    private String standCode;

    private String standDisplayValue;

    private String standDesc;

    private String stateCd;

    @TableField(exist = false)
    private Integer isDef;

    @TableField(exist = false)
    private Integer standIndex;

    private String standDisplayValueEn;

    private String standDisplayValueTw;

}