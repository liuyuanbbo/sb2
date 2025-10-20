package org.zmz.c.pojo.dataopen;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
@Table(name = "task_order")
public class TaskOrder {

    @TableId
    @Id
    private Long orderId;

    private String objType;

    private Long objId;

    private Long datasourceId;

    private String schemaCode;

    private String tableCode;

    /**
     * 状态值 TaskOrderLogStateEnum
     */
    private String state;

    private String startAcct;

    private Date createDate;

    private Long createStaff;

    private Long comAcctId;

    private String language;
}