package org.zmz.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.security.model.ObjInfo;
import org.zmz.security.service.ObjInfoService;
import org.zmz.security.vo.request.ObjInfoQueryQo;
import org.zmz.security.vo.response.R;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/objInfo")
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

}
