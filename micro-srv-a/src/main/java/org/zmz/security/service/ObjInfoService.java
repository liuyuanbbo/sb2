package org.zmz.security.service;

import org.springframework.stereotype.Service;
import org.zmz.security.mapper.dataopen.ObjInfoMapper;
import org.zmz.security.model.ObjInfo;
import org.zmz.security.vo.request.ObjInfoQueryQo;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ObjInfoService {

    @Resource
    ObjInfoMapper objInfoMapper;

    public LocalDateTime selectNow() {
        return objInfoMapper.selectNow();
    }

    public List<ObjInfo> queryConditionByChoose(ObjInfoQueryQo qo) {
        return objInfoMapper.queryConditionByChoose(qo);
    }

    public List<ObjInfo> queryCondition(ObjInfoQueryQo qo) {
        return objInfoMapper.queryCondition(qo);
    }

}
