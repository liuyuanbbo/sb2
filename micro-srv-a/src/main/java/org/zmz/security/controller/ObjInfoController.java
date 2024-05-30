package org.zmz.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;
import org.zmz.security.model.ObjInfo;
import org.zmz.security.service.ObjInfoService;
import org.zmz.security.vo.request.ObjInfoQueryQo;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/objInfo")
@Slf4j
public class ObjInfoController {

    @Resource
    private ObjInfoService objInfoService;

    @GetMapping("/selectNow")
    public R<LocalDateTime> selectNow() {
        return R.ok(objInfoService.selectNow());
    }

    @PostMapping("/queryCondition")
    public R<List<ObjInfo>> queryCondition(@RequestBody ObjInfoQueryQo qo) {
        Integer qoFlag = qo.getQoFlag();
        List<ObjInfo> objInfos = null;
        if (qoFlag == 1) {
            objInfos = objInfoService.queryConditionByChoose(qo);
        }
        if (qoFlag == 2) {
            objInfos = objInfoService.queryCondition(qo);
        }
        return R.ok(objInfos);
    }

    @GetMapping("/selectAll")
    public R<List<Long>> selectAll() {
        List<ObjInfo> objInfos = objInfoService.selectAll();
        log.info("objInfos size: {}", objInfos.size());
        List<Long> metaTableIdList = new ArrayList<>();
        Map<Long, Long> map = objInfos.stream().collect(
                Collectors.toMap(ObjInfo::getObjectId,
                        ObjInfo::getMetaTableId, (v1, v2) -> {
                            log.info("v1:{},v2:{}", v1, v2);
                            metaTableIdList.add(v1);
                            metaTableIdList.add(v2);
                            return v2;
                        }));
        return R.ok(metaTableIdList);
    }

}
