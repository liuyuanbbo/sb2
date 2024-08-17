package org.zmz.d.controller.dev154.dataportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;
import org.zmz.d.pojo.dev154.dataportal.StanTag;
import org.zmz.d.service.dev154.dataportal.StanTagService;

import java.util.List;
import java.util.Map;

/**
 * @author Zmz
 */
@RestController
@RequestMapping("/dev154/dataportal/stanTag")
public class StanTagController {

    StanTagService stanTagService;

    @Autowired
    public void setStanTagService(StanTagService stanTagService) {
        this.stanTagService = stanTagService;
    }

    @PostMapping("/getStanTagCache")
    public R<Map<String, List<StanTag>>> getStanTagCache() {
        Map<String, List<StanTag>> stanTagCacheMap = stanTagService.getStanTagCache();
        return R.ok(stanTagCacheMap);
    }

    @PostMapping("/findAll")
    public R<List<StanTag>> findAll() {
        List<StanTag> stanTags = stanTagService.findAll();
        return R.ok(stanTags);
    }

}
