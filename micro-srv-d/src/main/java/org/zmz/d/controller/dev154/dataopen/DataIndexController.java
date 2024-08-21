package org.zmz.d.controller.dev154.dataopen;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;
import org.zmz.d.service.dev154.dataopen.DataIndexService;

/**
 * @author Zmz
 */
@RestController
@RequestMapping("/DataIndexController")
public class DataIndexController {

    DataIndexService dataIndexService;

    @Autowired
    public void setDataIndexService(DataIndexService dataIndexService) {
        this.dataIndexService = dataIndexService;
    }

    @PostMapping("/buildSql")
    public R<String> buildSql(@RequestBody Map<String, Object> param) {
        String sql = dataIndexService.buildSql(param);
        return R.ok(sql);
    }
}
