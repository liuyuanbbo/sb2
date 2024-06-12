package org.zmz.security.controller.dataportal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;
import org.zmz.security.service.dataportal.StanAuditOrderService;
import org.zmz.security.vo.request.dataportal.BatchInsertDaAuditOrderReq;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/stanAuditOrder")
@Slf4j
public class StanAuditOrderController {

    @Resource
    private StanAuditOrderService stanAuditOrderService;

    @PostMapping("/batchInsertDaAuditOrder")
    public R<Long> batchInsertDaAuditOrder(@RequestBody List<BatchInsertDaAuditOrderReq> list) {
        Long count = stanAuditOrderService.batchInsertDaAuditOrder(list);
        log.info("插入行数: {}", count);
        return R.ok(count);
    }

}
