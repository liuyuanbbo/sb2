package org.zmz.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;
import org.zmz.security.model.ObjDataHis;
import org.zmz.security.service.ObjDataHisService;
import org.zmz.security.vo.request.ObjDataHisQo;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/objDataHis")
@Slf4j
public class ObjDataHisController {

    @Resource
    ObjDataHisService objDataHisService;

    @PostMapping("/selectRecentTwoRecords")
    public R<List<ObjDataHis>> selectRecentTwoRecords(@RequestBody ObjDataHisQo qo) {
        return R.ok(objDataHisService.selectRecentTwoRecords(qo));
    }

    @GetMapping("/syncMysqlDataToPostgres")
    public R<?> syncMysqlDataToPostgres() {
        objDataHisService.syncMysqlDataToPostgres();
        return R.ok();
    }

}
