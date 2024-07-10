package org.zmz.c.service.dataopen.remote;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ModelInfoQo;
import org.zmz.c.qo.dataopen.PhysicsModelQo;
import org.zmz.c.service.dataopen.feign.FeignDataCommonService;
import org.zmz.c.service.exception.BaseException;
import org.zmz.c.utils.JsonUtil;
import org.zmz.c.utils.ResponseUtil;
import org.zmz.c.vo.dataopen.dataset.PhysicsTableResponseVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public ModelInfo queryAllModelInfoPro(Long metaDataId) {
        ModelInfoQo modelInfoQo = new ModelInfoQo();
        modelInfoQo.setMetaDataIdList(Collections.singletonList(metaDataId));
        List<ModelInfo> modelInfos = this.queryAllModelInfoBatch(modelInfoQo);
        if (CollUtil.isNotEmpty(modelInfos)) {
            return modelInfos.get(0);
        }
        return null;
    }

    public ModelInfo getTimeModel(String tableType) {
        // 获取时间维表
        ModelInfoQo modelInfoQo = new ModelInfoQo();
        modelInfoQo.setIsTimeDimension(Constants.YES_VALUE_1);
        modelInfoQo.setTableType(tableType);
        List<ModelInfo> modelInfoList = this.queryAllModelInfoBatch(modelInfoQo);
        if (CollUtil.isEmpty(modelInfoList)) {
            return null;
        }
        return modelInfoList.get(0);
    }

    /**
     * 获取落地的物理表
     */
    public List<PhysicsTableResponseVo> getPhysicsTableListPro(PhysicsModelQo physicsModelQo) {
        String module = "physicsmodel/getTableList";
        log.info("获取落地的物理表{}入参:{}", module, JsonUtil.toJson(physicsModelQo));

        ResponseUtil responseUtil = feignDataCommonService.getTableList(physicsModelQo);
        isSuccess(responseUtil);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(responseUtil.getResultObject());
        return jsonObject.getJSONArray("tableList").toJavaList(PhysicsTableResponseVo.class);
    }

    public void isSuccess(ResponseUtil responseUtil) {
        if (!responseUtil.isSuccess()) {
            log.info("公共服务接口返回失败信息: {}", JSONObject.toJSONString(responseUtil));
            throw new BaseException(responseUtil.getResultMsg());
        }
    }
}
