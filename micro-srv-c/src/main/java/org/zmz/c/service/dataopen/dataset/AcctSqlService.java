package org.zmz.c.service.dataopen.dataset;

import org.springframework.stereotype.Service;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.utils.AcctTimeUtil;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AcctSqlService {
    /**
     * 预览账期周期
     */
    public String getDataCycle(Collection<ModelInfo> modelInfoList) {
        // 按最细账期到最粗
        Set<String> dataCycles = modelInfoList.stream().map(modelInfo -> modelInfo.getMetaDataInfo().getDataCycleCode())
                .collect(Collectors.toSet());
        return AcctTimeUtil.getCycleType(dataCycles);
    }
}
