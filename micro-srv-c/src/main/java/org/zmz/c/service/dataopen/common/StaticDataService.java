package org.zmz.c.service.dataopen.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.zmz.c.mapper.dataportal.DcSystemConfigMapper;
import org.zmz.c.pojo.dataportal.DcSystemConfig;

import javax.annotation.Resource;

import static org.zmz.c.qo.dataopen.Constants.DATAOPEM_DCSYS_KEY_PREFIX;

@Service
public class StaticDataService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    RedisTemplate<Object, Object> redisTemplate;

    @Resource
    DcSystemConfigMapper dcSystemConfigMapper;

    public String getDcSystemParamByCache(String code, String defaultValue) {
        String value = this.getDcSystemParamByCache(code);
        if (StringUtils.isNotEmpty(value)) {
            return value.trim();
        }
        return defaultValue;
    }

    /**
     * 读取系统表静态参数，key-value
     */
    public String getDcSystemParamByCache(String code) {

        String dcsysParamRedisKey = DATAOPEM_DCSYS_KEY_PREFIX + "_" + code;
        String value = stringRedisTemplate.opsForValue().get(dcsysParamRedisKey);
        if (StringUtils.isNotEmpty(value)) {
            return value.trim();
        }
        // 如果没有就从数据库读取
        DcSystemConfig param = dcSystemConfigMapper.selectByPrimaryKey(code);
        value = (param == null || StringUtils.isEmpty(param.getParamValue()) ? "" : param.getParamValue());
        stringRedisTemplate.opsForValue().set(dcsysParamRedisKey, value);
        return value;
    }

}
