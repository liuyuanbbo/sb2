package org.zmz.c.controller.dataopen.dataindex;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.service.dataopen.dataindex.DataIndexService;
import org.zmz.common.R;

/**
 * @author Zmz
 */
@RestController
@RequestMapping("/DataIndexController")
public class DataIndexController {

    @Resource
    DataIndexService dataIndexService;

    /**
     * 数据指标生成 SQL
     */
    @PostMapping(value = "/generateSql")
    public R<String> generateSql(@RequestBody DatasetDetail params) {
        String sql = dataIndexService.generateSql(params);
        return R.ok(sql);
    }

}
