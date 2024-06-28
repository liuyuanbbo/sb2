package org.zmz.c.controller.dataopen.dataset;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.service.dataopen.dataset.DataSetService;
import org.zmz.common.R;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dataset")
public class DataSetController {

    @Resource
    DataSetService dataSetService;

    /**
     * SQL预览
     */
    @PostMapping(value = "/previewSql")
    public R<?> previewSql(@RequestBody DatasetDetail params) {
        return R.ok(dataSetService.previewSql(params));
    }
}
