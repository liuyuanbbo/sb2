package org.zmz.c.pojo.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Zmz
 */
@Setter
@Getter
@Table(name = "PRO_RESTRICT_COLUMN")
public class ProRestrictColumn {

    @Id
    @TableId
    private Long restrictColumnId;

    private Long restrictId;

    private String columnExpress;

    private String connectType;

    private String connectOperator;

    private String connectValue;

    private String connectName;

    private Integer seq;

    private Long columnId;

    private Long tableId;

    private String columnCode;

    private String tableCode;

    private String state;

    /**
     * 账期相对时间表达式
     */
    private String comments;
}