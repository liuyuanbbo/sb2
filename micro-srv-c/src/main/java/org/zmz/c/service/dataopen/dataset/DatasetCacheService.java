package org.zmz.c.service.dataopen.dataset;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.zmz.c.mapper.dataopen.ObjKeyTableRelaMapper;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaQo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.zmz.c.qo.dataopen.Constants.DATASET_DIM_RELA_MAP;
import static org.zmz.c.qo.dataopen.Constants.DATASET_KEY_RELA_LIST;
import static org.zmz.c.qo.dataopen.Constants.TIMEOUT;

@Service
public class DatasetCacheService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private ObjKeyTableRelaMapper objKeyTableRelaMapper;

    /**
     * 从缓存中取对象关系，取不到查库
     *
     * @param comAcctId 企管账号
     */
    public List<ObjKeyTableRelaVo> getDatasetKeyRelaListInRedis(Long comAcctId) {
        String key = DATASET_KEY_RELA_LIST + "_" + comAcctId;
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String data = stringValueOperations.get(key);
        if (StringUtils.isEmpty(data)) {
            ObjKeyTableRelaQo obj = new ObjKeyTableRelaQo();
            obj.setRelaType(Constants.KEY_TYPE_FOREIGN);
            obj.setComAcctId(comAcctId);
            List<ObjKeyTableRelaVo> list = objKeyTableRelaMapper.selectKeyRelaByQo(obj);
            stringValueOperations.set(key, JSON.toJSONString(list), TIMEOUT, TimeUnit.MILLISECONDS);
            return list;
        }
        return JSON.parseArray(data, ObjKeyTableRelaVo.class);
    }

    /**
     * 先从redis查询派生指标的统计粒度
     */
    public Map<Long, List<ObjKeyTableRelaVo>> getObjDimRelaMapInRedis(Long comAcctId) {
        String redisKey = DATASET_DIM_RELA_MAP + "_" + comAcctId;
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String data = stringValueOperations.get(redisKey);
        if (StringUtils.isEmpty(data)) {
            ObjKeyTableRelaQo obj = new ObjKeyTableRelaQo();
            obj.setReferType(Constants.REFER_TYPE_INDEX);
            obj.setComAcctId(comAcctId);
            List<ObjKeyTableRelaVo> list = objKeyTableRelaMapper.selectKeyRelaByQo(obj);
            Map<Long, List<ObjKeyTableRelaVo>> objDimRelaVosMap = list.stream()
                .collect(Collectors.groupingBy(ObjKeyTableRelaVo::getObjectId));
            stringValueOperations.set(redisKey, JSON.toJSONString(objDimRelaVosMap), TIMEOUT, TimeUnit.MILLISECONDS);
            return objDimRelaVosMap;
        }
        return JSON.parseObject(data, new TypeReference<>() {
        });
    }

}
