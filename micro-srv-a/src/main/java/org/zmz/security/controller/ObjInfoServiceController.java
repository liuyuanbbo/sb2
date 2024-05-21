package org.zmz.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.security.service.ObjInfoService;
import org.zmz.security.vo.response.R;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/objInfo")
public class ObjInfoServiceController {

    @Resource
    private ObjInfoService objInfoService;

    @GetMapping("/selectNow")
    public R<LocalDateTime> selectNow() {
        return R.ok(objInfoService.selectNow());
    }

}
