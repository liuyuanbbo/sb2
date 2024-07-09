package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReplaceParamQo {

    /**
     * 待替换的表达式
     */
    private String originalValue;

    /**
     * 待替换的账期，默认当前时间
     */
    private String acct;

    /**
     * 任务id，根据任务获取最新实例，默认查询成功状态
     */
    private Long taskId;

    /**
     * 根据任务状态获取最新实例
     */
    private List<String> taskStates;
}