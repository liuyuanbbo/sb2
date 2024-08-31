package org.zmz.c.controller.dataopen.dataindex;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.c.service.dataopen.dataindex.ApiSqlService;
import org.zmz.common.R;

/**
 * @author Zmz
 */
@RestController
@RequestMapping("/ApiSqlController")
public class ApiSqlController {

    @Resource
    ApiSqlService apiSqlService;

    /**
     * 利用 Ali Druid 解析 SQL 中的出入参
     */
    @PostMapping(value = "/parseSqlApiParam")
    public R<String> parseSqlApiParam(@RequestBody Map<String, Object> param) {
        String sql = apiSqlService.parseSqlApiParam(param);
        return R.ok(sql);
    }

}
