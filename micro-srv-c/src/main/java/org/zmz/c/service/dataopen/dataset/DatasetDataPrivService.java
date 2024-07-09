package org.zmz.c.service.dataopen.dataset;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zmz.c.qo.dataopen.DatasetDetail;

@Service
public class DatasetDataPrivService {
    public int getDataPrivGroupCount(DatasetDetail params) {
        if (CollectionUtils.isEmpty(params.getGroups())) {
            return 0;
        }
        return (int) params.getGroups().stream().filter(v -> v.getDataPrivCtrlInfo().isDataPrivCtrl()).count();
    }
}
