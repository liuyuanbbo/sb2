package org.zmz.c.controller.dataopen.dataset;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.c.service.dataopen.dataset.IndexPreviewService;
import org.zmz.common.R;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/indexPreview")
public class IndexPreviewController {

    @Resource
    IndexPreviewService indexPreviewService;

    /**
     * 新增收藏
     */
    @PostMapping(value = "/addCollect")
    public R<?> addCollect(@RequestBody Map<String, Object> params) {
        return R.ok(indexPreviewService.addCollect(params));
    }

}
