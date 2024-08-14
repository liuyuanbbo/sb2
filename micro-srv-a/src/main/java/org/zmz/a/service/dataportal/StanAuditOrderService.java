package org.zmz.a.service.dataportal;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import org.zmz.a.mapper.dataportal.StanAuditOrderMapper;
import org.zmz.a.vo.request.dataportal.BatchInsertDaAuditOrderReq;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StanAuditOrderService {

    @Resource
    StanAuditOrderMapper stanAuditOrderMapper;

    public Long batchInsertDaAuditOrder(List<BatchInsertDaAuditOrderReq> reqList) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (BatchInsertDaAuditOrderReq req : reqList) {
            Map<String, Object> map = BeanUtil.beanToMap(req);
            map.put("createDate", new Date());
            map.put("createBy", 1001);
            map.put("comAcctId", 1001);
            list.add(map);
        }
        return stanAuditOrderMapper.batchInsertDaAuditOrder(list);
    }

}
