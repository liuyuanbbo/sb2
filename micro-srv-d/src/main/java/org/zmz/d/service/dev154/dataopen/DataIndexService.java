package org.zmz.d.service.dev154.dataopen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.zmz.d.mapper.dev154.dataopen.IndexMapper;
import org.zmz.d.pojo.dev154.dto.CondOperatorDto;
import org.zmz.d.pojo.dev154.dto.IndexDto;
import org.zmz.d.pojo.dev154.dto.ObjInfoDto;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zmz
 */
@Slf4j
@Service
public class DataIndexService {

    @Resource
    IndexMapper indexMapper;

    private static List<CondOperatorDto> COND_OPERATOR_DTO_LIST = null;

    static {
        ClassPathResource resource = new ClassPathResource("COND_OPERATOR.json");
        try {
            byte[] bytes = Files.readAllBytes(Path.of(resource.getPath()));
            COND_OPERATOR_DTO_LIST = JSONObject.parseArray(new String(bytes), CondOperatorDto.class);
        }
        catch (IOException e) {
            System.exit(0);
        }
    }

    private static final AtomicInteger TB_INCREMENT_COUNTER = new AtomicInteger(1);

    private static String getTableAlias() {
        return "tb" + TB_INCREMENT_COUNTER.getAndIncrement();
    }

    public String buildSql(Map<String, Object> param) {
        if (CollectionUtils.isNotEmpty(COND_OPERATOR_DTO_LIST)) {
            log.info("条件表达式列表: {}", COND_OPERATOR_DTO_LIST);
        }
        Set<Long> objectIds = MapUtil.get(param, "objectIds", new TypeReference<>() {
        });
        Set<ObjInfoDto> objInfoDtos = indexMapper.selectObjInfo(objectIds);
        // 维度参数

        // where条件

        // 指标参数
        Set<IndexDto> indexDtos = indexMapper.unionAllProIndexAndDimIndex(objectIds);

        // 用完 1
        TB_INCREMENT_COUNTER.set(1);
        return null;
    }
}
