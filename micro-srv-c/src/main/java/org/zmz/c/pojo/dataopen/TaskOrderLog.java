package org.zmz.c.pojo.dataopen;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
@Table(name = "task_order_log")
public class TaskOrderLog {
    @TableId
    @Id
    private Long logId;

    private Long orderId;

    private String logName;

    private String logCode;

    private String logMsg;

    /**
     * 执行任务日志状态，0 历史归档， 1 当前日志
     */
    private String logStatus;

    /**
     * 状态值 TaskOrderLogStateEnum
     */
    private String state;

    private Date createDate;

    /**
     * 任务执行账期
     */
    private String acct;
}