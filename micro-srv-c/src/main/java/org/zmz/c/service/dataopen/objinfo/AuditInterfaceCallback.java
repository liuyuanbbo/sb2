package org.zmz.c.service.dataopen.objinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zmz 通用审核回调辅助类用于更新状态
 */
public interface AuditInterfaceCallback {

    Logger LOG = LoggerFactory.getLogger(AuditInterfaceCallback.class);

    default <T> void defaultChangeStatus(T t) {
        Object o = getEntityByOrderNo(new HashMap<>(), t.getClass());
        LOG.info("||||||||||||||||||||更新上下架状态|||||||||||||||||||||||");
        commitTransaction();
    }

    <T> void changeStatus(T t);

    default <T> T getEntityByOrderNo(Map<String, Object> params, Class<T> clz) {
        LOG.info("===================根据id获取数据库实体=======================");
        return null;
    }

    default void commitTransaction() {
        LOG.info(">>>>>>>>>>>>>>>>>>>提交事务>>>>>>>>>>>>>>>>>>");
    }

}
