package org.zmz.c.service.dataopen.common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.zmz.c.mapper.dataopen.ObjInfoMapper;
import org.zmz.c.mapper.dataopen.StaticDataMapper;
import org.zmz.c.mapper.dataportal.DcSystemConfigMapper;
import org.zmz.c.pojo.dataopen.AttrValue;
import org.zmz.c.pojo.dataportal.DcSystemConfig;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.utils.I18nUtil;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static org.zmz.c.qo.dataopen.Constants.DATAOPEM_DCSYS_KEY_PREFIX;
import static org.zmz.c.qo.dataopen.Constants.SMART_ATTR_KEY_PREFIX;

@Service
public class StaticDataService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    DcSystemConfigMapper dcSystemConfigMapper;

    @Resource
    ObjInfoMapper objInfoMapper;

    @Resource
    StaticDataMapper staticDataMapper;

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

    /**
     * 根据编码获取静态参数列表,已经包含了国际化的转换
     */
    public List<AttrValue> getAttrValueByCode(String code) {
        String language = I18nUtil.getLanguage();
        String attrRedisKey = SMART_ATTR_KEY_PREFIX + "_" + code;
        // 先从dc_sql表查询
        List<AttrValue> attrValueList = this.execStaticAttrDcsqlByCode(code);
        if (CollUtil.isEmpty(attrValueList)) {
            // dc_sql表没有，从缓存取
            attrValueList = JSONUtil.toBean(stringRedisTemplate.opsForValue().get(attrRedisKey), new TypeReference<>() {
            }, false);
            if (CollUtil.isEmpty(attrValueList)) {
                //attrRedisKey = RedisUtil.getDcSqlRedisKey(code);
                //attrValueList = RedisUtil.get(attrRedisKey);
                if (CollUtil.isEmpty(attrValueList)) {
                    // 缓存找不到，最后从attrValue表查询
                    //attrValueList = staticDataMapper.getAttrValueByCode(code, language);
                    //attrRedisKey = RedisUtil.getAttrRedisKey(code);
                    //RedisUtil.set(attrRedisKey, attrValueList);
                }
            }
        }

        // 对静态参数进行国际化信息替换
        //attrValueList = setAttrValueI18n(attrValueList);
        return attrValueList;
    }

    /**
     * 根据编码执行sql,sql查询的值必须与attr_value映射一致
     */
    private List<AttrValue> execStaticAttrDcsqlByCode(String code) {
        // INJECTION_LABEL_OWM_SYSTEM要查询组织表，组织表已去视图，改为调接口查
        if (Constants.DC_SQL_INJECTION_LABEL_OWM_SYSTEM.equals(code)) {
            //return getOwnSystem();
            return null;
        }

        String dcSql = staticDataMapper.getStaticAttrDcsqlByCode(code);
        if (StringUtils.isEmpty(dcSql)) {
            return Collections.emptyList();
        }

        //dcSql = SqlReplaceUtil.handleSqlReplace(dcSql, Collections.emptyMap());

        //return staticDataMapper.execStaticDcsqlGetAttrValueList(dcSql);
        return null;
    }

    /**
     * INJECTION_LABEL_OWM_SYSTEM要查询组织表，组织表已去视图，改为调接口查
     *
     * @return 组织数据封成的AttrValue列表
     */
//    private List<AttrValue> getOwnSystem() {
//        OrganizationQo organizationQo = new OrganizationQo();
//        organizationQo.setOrgId(null);
//        Result<PageVO<Organization>> pageVo = comsystemService.qryOrgList(organizationQo);
//        List<Organization> rows = pageVo.getData().getRows();
//        return rows.stream().map(organization -> {
//            AttrValue attrValue = new AttrValue();
//            attrValue.setAttrId(organization.getOrgId());
//            attrValue.setAttrValueName(organization.getOrgName());
//            return attrValue;
//        }).collect(Collectors.toList());
//    }

}
