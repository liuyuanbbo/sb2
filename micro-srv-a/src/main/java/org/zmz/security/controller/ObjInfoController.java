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
        return R.ok(objInfoService.queryCondition(qo));
    }

}
