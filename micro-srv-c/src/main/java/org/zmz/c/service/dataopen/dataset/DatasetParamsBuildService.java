package org.zmz.c.service.dataopen.dataset;

import org.springframework.stereotype.Service;
import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetDetail;

import javax.annotation.Resource;

@Service
public class DatasetParamsBuildService {

    @Resource
    DatasetTermService datasetTermService;

    public void paramsBuild(DatasetDetail params) {
        // 术语处理，虚拟对象处理
        datasetTermService.buildDatasetColumns(params);
        // 第一组权限控制信息
        DataPrivCtrlVo firstDataPrivctrl = null;
        for (DatasetColumnAndConditionQo groupQo : params.getGroups()) {
            groupQo.setFirstDataPrivCtrlInfo(firstDataPrivctrl);
            this.paramsBuild(groupQo);
            // 多路径的组织字段以第一分组的为准
            if (firstDataPrivctrl == null && groupQo.getDataPrivCtrlInfo() != null) {
                firstDataPrivctrl = groupQo.getDataPrivCtrlInfo();
            }
        }
    }
}
