package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Constraint implements Serializable {

    /**
     * 1 唯一键
     */
    private String constraintType;

    /**
     * pk_order_item_id
     */
    private String constraintCode;

    /**
     * 表编码
     */
    private String tableCode;

    /**
     * order_item_id
     */
    private String columnCode;

    /**
     * 排序
     */
    private String constraintPosition;

    public Constraint() {

    }

    public Constraint(String columnCode) {
        this.constraintType = "1";
        this.constraintCode = "unique_" + columnCode;
        this.columnCode = columnCode;
    }
}
