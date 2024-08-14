package org.zmz.a.service;

import org.springframework.stereotype.Service;
import org.zmz.a.mapper.dataopen.ObjInfoMapper;
import org.zmz.a.model.dataopen.ObjInfo;
import org.zmz.a.vo.request.ObjInfoQueryQo;

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

    public List<ObjInfo> selectAll(){
        return objInfoMapper.selectAll();
    }


}
