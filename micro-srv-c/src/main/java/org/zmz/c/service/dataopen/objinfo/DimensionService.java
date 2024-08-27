package org.zmz.c.service.dataopen.objinfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Zmz
 */
@Service
@Slf4j
public class DimensionService implements AuditInterfaceCallback {

    @Override
    public <T> T getEntityByOrderNo(Map<String, Object> params, Class<T> clz) {
        log.info("?????????????????????  DimensionService getEntityByOrderNo ????????????????????");
        return null;
    }

    @Override
    public void commitTransaction() {
        log.info("BBBBBBBBBBBBBBBB  DimensionService commitTransaction TTTTTTTTTTTTTTTTTTT");
    }

    @Override
    public <T> void changeStatus(T t) {
        defaultChangeStatus(t);
    }

}
