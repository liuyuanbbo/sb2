package org.zmz.c.pojo.dataportal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@TableName(schema = "DC_SYSTEM_CONFIG_LIST")
@ToString
public class DcSystemConfigList implements Serializable {
    @TableId
    private Long id;

    private String standType;

    private String standCode;

    private String standDisplayValue;

    private String standDesc;

    private String stateCd;

    private Integer isDef;

    private Integer standIndex;

    private String standDisplayValueEn;

    private String standDisplayValueTw;

}