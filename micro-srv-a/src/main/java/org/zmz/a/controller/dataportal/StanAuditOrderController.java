package org.zmz.a.controller.dataportal;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;
import org.zmz.a.service.dataportal.StanAuditOrderService;
import org.zmz.a.vo.request.dataportal.BatchInsertDaAuditOrderReq;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stanAuditOrder")
@Slf4j
public class StanAuditOrderController {

    @Resource
    private StanAuditOrderService stanAuditOrderService;

    @PostMapping("/batchInsertDaAuditOrder")
    public R<Long> batchInsertDaAuditOrder(@RequestBody Map<String, Object> params) {
        List<BatchInsertDaAuditOrderReq> list = MapUtil.get(params, "reqList",
                new TypeReference<>() {
                });
        Long count = stanAuditOrderService.batchInsertDaAuditOrder(list);
        log.info("插入行数: {}", count);
        return R.ok(count);
    }

}
