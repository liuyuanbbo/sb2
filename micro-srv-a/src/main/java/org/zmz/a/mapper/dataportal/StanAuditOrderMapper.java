package org.zmz.a.mapper.dataportal;

import org.apache.ibatis.annotations.Param;
import org.zmz.a.model.StanAuditOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface StanAuditOrderMapper {
    //just for test
    LocalDateTime selectNow();

    //just for test
    List<StanAuditOrder> simpleList();

    // 批量插入 StanAuditOrder
    Long batchInsertDaAuditOrder(@Param("list") List<Map<String, Object>> list);
}
