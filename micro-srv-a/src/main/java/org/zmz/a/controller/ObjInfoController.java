package org.zmz.a.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.a.model.dataopen.ObjInfo;
import org.zmz.a.service.ObjInfoService;
import org.zmz.a.vo.request.ObjInfoQueryQo;
import org.zmz.common.R;

import lombok.extern.slf4j.Slf4j;

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
    public R<List<ObjInfo>> selectAll() {
        List<ObjInfo> objInfos = objInfoService.selectAll();
        log.info("objInfos size: {}", objInfos.size());
        return R.ok(objInfos);
    }

}
