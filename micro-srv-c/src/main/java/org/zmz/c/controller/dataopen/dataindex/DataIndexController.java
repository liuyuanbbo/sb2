package org.zmz.c.controller.dataopen.dataindex;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.service.dataopen.dataindex.DataIndexService;
import org.zmz.common.R;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dataIndexController")
public class DataIndexController {

    @Resource
    DataIndexService dataIndexService;

    /**
     * 数据指标生成 SQL
     */
    @PostMapping(value = "/previewSql")
    public R<String> previewSql(@RequestBody DatasetDetail params) {
        String sql = dataIndexService.generateSql(params);
        return R.ok(sql);
    }

}
