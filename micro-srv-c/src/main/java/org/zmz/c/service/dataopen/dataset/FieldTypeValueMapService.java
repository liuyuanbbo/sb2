package org.zmz.c.service.dataopen.dataset;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zmz.c.mapper.dataopen.ZmgrOmpFieldTypeValueMapMapper;
import org.zmz.c.pojo.dataopen.ZmgrOmpFieldTypeValueMap;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FieldTypeValueMapService {

    @Resource
    private ZmgrOmpFieldTypeValueMapMapper zmgrOmpFieldTypeValueMapMapper;

    public Map<String, String> getFieldTypeMap(String tableType) {
        List<ZmgrOmpFieldTypeValueMap> fieldTypeMapList = this.getFieldTypeMapList(tableType);
        if (CollectionUtils.isEmpty(fieldTypeMapList)) {
            return Collections.emptyMap();
        }
        return fieldTypeMapList.stream()
                .collect(Collectors.toMap(ZmgrOmpFieldTypeValueMap::getTypeValue, ZmgrOmpFieldTypeValueMap::getTypeString));
    }

    private List<ZmgrOmpFieldTypeValueMap> getFieldTypeMapList(String tableType) {
        ZmgrOmpFieldTypeValueMap fieldTypeValueMapQo = new ZmgrOmpFieldTypeValueMap();
        fieldTypeValueMapQo.setTableType(tableType);
        return zmgrOmpFieldTypeValueMapMapper.select(fieldTypeValueMapQo);
    }
}
