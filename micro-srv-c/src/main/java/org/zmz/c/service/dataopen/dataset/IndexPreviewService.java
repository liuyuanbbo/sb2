package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zmz.c.mapper.dataopen.DopUserFavorLogMapper;
import org.zmz.c.pojo.dataopen.DopUserFavorLog;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.utils.AccountUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Zmz
 */
@Service
public class IndexPreviewService {

    @Resource
    DopUserFavorLogMapper dopUserFavorLogMapper;

    @Transactional(rollbackFor = Exception.class)
    public Object addCollect(Map<String, Object> params) {
        List<Map<String, Object>> datas = MapUtil.get(params, "datas", new TypeReference<List<Map<String, Object>>>() {
        });
        for (Map<String, Object> para : datas) {
            DopUserFavorLog dopUserFavorLog = new DopUserFavorLog();
            // Long logId = sysService.updateDataopentNextval("SEQ_DOP_USER_FAVOR_LOG");
            String dataIdsStr = MapUtil.getStr(para, "serviceId");
            //dopUserFavorLog.setLogId(logId);
            dopUserFavorLog.setDataId(Long.parseLong(dataIdsStr));
            dopUserFavorLog.setDataType(MapUtil.getStr(para, "dataType"));
            dopUserFavorLog.setUserId(AccountUtil.getSysUserId());
            dopUserFavorLog.setFavoriteDate(new Date());
            dopUserFavorLog.setStatusCd(Constants.STATUS_CD_00A);

            dopUserFavorLogMapper.insert(dopUserFavorLog);
        }
        return null;
    }
}
