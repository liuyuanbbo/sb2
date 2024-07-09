package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.zmz.c.pojo.dataopen.ObjKeyTableRela;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;
import org.zmz.c.utils.JsonUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.zmz.c.qo.dataopen.Constants.DATASET_OBJ_PATH_MAP;
import static org.zmz.c.qo.dataopen.Constants.TIMEOUT;

@Service
@Slf4j
public class ObjKeyPathService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    DatasetCacheService datasetCacheService;

    //@Resource
    //AccountUtil accountUtil;

    /**
     * 对象两两之间关系
     */
    public Map<String, Set<List<Long>>> getDatasetObjPathMap() {
        //Long comAcctId = accountUtil.getComAcctId();
        long comAcctId = 1021L;
        String datasetObjPathMapRedisKey = DATASET_OBJ_PATH_MAP + "_" + comAcctId;
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String cacheDatasetObjPathMap = stringValueOperations.get(datasetObjPathMapRedisKey);
        if (StrUtil.isNotEmpty(cacheDatasetObjPathMap)) {
            return JSON.parseObject(cacheDatasetObjPathMap, new TypeReference<>() {
            });
        }

        // 获取关联关系
        List<ObjKeyTableRelaVo> objKeyTableRelaList = datasetCacheService.getDatasetKeyRelaListInRedis(comAcctId);
        // 构建后放入缓存
        this.buildObjKeyPaths(objKeyTableRelaList);
        cacheDatasetObjPathMap = stringValueOperations.get(datasetObjPathMapRedisKey);
        return JSON.parseObject(cacheDatasetObjPathMap, new TypeReference<>() {
        });
    }

    /**
     * 两个对象间的所有路径
     */
    public void buildObjKeyPaths(List<ObjKeyTableRelaVo> objKeyTableRelas) {
        Map<String, Set<List<Long>>> pathsMap = new HashMap<>();
        // 直接关系
        for (ObjKeyTableRela objKeyTableRela : objKeyTableRelas) {
            String pathKey = getPathKey(objKeyTableRela.getObjectId(), objKeyTableRela.getRelaKeyObjectId());
            Set<List<Long>> relas = pathsMap.getOrDefault(pathKey, new HashSet<>());
            relas.add(Collections.singletonList(objKeyTableRela.getRelaId()));
            pathsMap.put(pathKey, relas);
        }
        // 间接关系
        for (ObjKeyTableRelaVo nKeyTableRela : objKeyTableRelas) {
            // 找到一端的一端
            List<ObjKeyTableRelaVo> oneKeyRelas = objKeyTableRelas.stream()
                    .filter(keyRela -> nKeyTableRela.getRelaKeyObjectId().equals(keyRela.getObjectId()))
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(oneKeyRelas)) {
                // 找到一端的一端,将路径加到当前对象上
                for (ObjKeyTableRelaVo oneKeyRela : oneKeyRelas) {
                    // 递归次数
                    int recurCount = 0;
                    recurBuildPath(nKeyTableRela, oneKeyRela, objKeyTableRelas, pathsMap, recurCount);
                }
            }
        }

        for (Map.Entry<String, Set<List<Long>>> entry : pathsMap.entrySet()) {
            log.info("{} : {}",
                    JsonUtil.toJson(entry.getKey()), JsonUtil.toJson(entry.getValue()) + System.lineSeparator()
            );
        }
        //Long comAcctId = accountUtil.getComAcctId();
        long comAcctId = 1021L;
        String datasetObjPathMapRedisKey = DATASET_OBJ_PATH_MAP + "_" + comAcctId;
        String jsonString = JSON.toJSONString(pathsMap);
        stringRedisTemplate.opsForValue().set(datasetObjPathMapRedisKey, jsonString, TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private void recurBuildPath(ObjKeyTableRelaVo nKeyTableRela,
                                ObjKeyTableRelaVo oneKeyTableRela,
                                List<ObjKeyTableRelaVo> objKeyTableRelas,
                                Map<String, Set<List<Long>>> pathsMap,
                                int recurCount) {
        // 关系成环，容易死循环
        if (recurCount > 20) {
            throw new RuntimeException(
                    String.format("对象关联关系配置成环了，请检查N端: %s , 1端: %s",
                            nKeyTableRela.getTableCode(), oneKeyTableRela.getTableCode()));
        }

        String pathKey = getPathKey(nKeyTableRela.getObjectId(), oneKeyTableRela.getRelaKeyObjectId());
        Set<List<Long>> relas = pathsMap.getOrDefault(pathKey, new HashSet<>());

        Set<List<Long>> nRelas = pathsMap.get(getPathKey(nKeyTableRela.getObjectId(), oneKeyTableRela.getObjectId()));
        // 一端的一端
        Set<List<Long>> oneRelas = pathsMap
                .get(getPathKey(oneKeyTableRela.getObjectId(), oneKeyTableRela.getRelaKeyObjectId()));
        // 笛卡尔积
        List<List<Long>> joinRelas = new ArrayList<>(nRelas.size() * oneRelas.size());
        for (List<Long> nRela : nRelas) {
            for (List<Long> oneRela : oneRelas) {
                List<Long> joinRela = new ArrayList<>(nRela);
                joinRela.addAll(oneRela);
                joinRelas.add(joinRela);
            }
        }
        relas.addAll(joinRelas);

        pathsMap.put(pathKey, relas);

        // 找到一端的一端
        List<ObjKeyTableRelaVo> oneOneKeyRelas = objKeyTableRelas.stream()
                .filter(keyRela -> oneKeyTableRela.getRelaKeyObjectId().equals(keyRela.getObjectId()))
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(oneOneKeyRelas)) {
            // 找到一端的一端,将路径加到当前对象上
            for (ObjKeyTableRelaVo oneOneKeyRela : oneOneKeyRelas) {
                recurBuildPath(nKeyTableRela, oneOneKeyRela, objKeyTableRelas, pathsMap, ++recurCount);
            }
        }
    }

    /**
     * key值固定，n端对象～1端对象
     *
     * @param nObjectId   nObjectId
     * @param oneObjectId oneObjectId
     */
    public String getPathKey(Long nObjectId, Long oneObjectId) {
        return nObjectId + "," + oneObjectId;
    }

}
