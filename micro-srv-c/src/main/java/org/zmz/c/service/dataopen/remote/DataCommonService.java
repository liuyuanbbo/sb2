package org.zmz.c.service.dataopen.remote;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ModelInfoQo;
import org.zmz.c.service.dataopen.feign.FeignDataCommonService;
import org.zmz.c.utils.JsonUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataCommonService {

    @Resource
    FeignDataCommonService feignDataCommonService;

    public Map<Long, ModelInfo> queryAllModelInfoMap(Collection<Long> metaTableIds) {
        ModelInfoQo modelInfoQo = new ModelInfoQo();
        modelInfoQo.setMetaDataIdList(new ArrayList<>(metaTableIds));
        return queryAllModelInfoMap(modelInfoQo);
    }

    public List<ModelInfo> queryAllModelInfoList(Collection<Long> metaTableIds) {
        ModelInfoQo modelInfoQo = new ModelInfoQo();
        modelInfoQo.setMetaDataIdList(new ArrayList<>(metaTableIds));
        return this.queryAllModelInfoBatch(modelInfoQo);
    }

    public Map<Long, ModelInfo> queryAllModelInfoMap(ModelInfoQo params) {
        List<ModelInfo> modelInfoList = this.queryAllModelInfoBatch(params);
        // 构建表map
        return modelInfoList.stream().collect(Collectors.toMap(modelInfo -> modelInfo.getMetaDataInfo().getMetaDataId(),
                modelInfo -> modelInfo, (v1, v2) -> v1));
    }

    /**
     * 会返回errorCode
     */
    public List<ModelInfo> queryAllModelInfoBatch(ModelInfoQo params) {
        long startTime = System.currentTimeMillis();
        String module = "ModelMgrController/queryModelInfoBatch";
        log.info("获取模型详情{}入参:{}", module, JsonUtil.toJson(params));
        Map<?, ?> resultMap = feignDataCommonService.queryAllModelInfoBatch(params);

        if (!"0".equalsIgnoreCase(MapUtil.getStr(resultMap, "resultCode"))) {
            String resultMsg = MapUtil.getStr(resultMap, "resultMsg");
            int errorCode = MapUtil.getInt(resultMap, "errorCode", 0);
            log.info("公共服务接口返回失败信息: {}", resultMsg);
            if ("metaDataIdList is empty ,Parameter error!".equals(resultMsg) || 20064 == errorCode) {
                return null;
            }
        }
        Object resultObject = resultMap.get("resultObject");
        JSONArray jsonArray = (JSONArray) JSON.toJSON(resultObject);

        List<ModelInfo> modelInfos = jsonArray.toJavaList(ModelInfo.class);
        if (CollUtil.isNotEmpty(modelInfos)) {
            // 排除空的模型
            modelInfos = modelInfos.stream()
                    .filter(table -> (table.getMetaDataInfo() != null && table.getMetaDataInfo().getMetaDataId() != null))
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(modelInfos)) {
                modelInfos.forEach(
                        modelInfo -> modelInfo.getMetaDataInfo().setSchemaCode(modelInfo.getMetaDataInfo().getDatabase()));
            }
        }
        log.info("获取模型详情接口耗时:{}", System.currentTimeMillis() - startTime);
        return modelInfos;
    }
}
