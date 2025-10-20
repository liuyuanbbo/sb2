package org.zmz.a.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmz.a.mapper.dataopen.ObjDataHisMapper;
import org.zmz.a.model.dataopen.ObjDataHis;
import org.zmz.a.vo.request.ObjDataHisQo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ObjDataHisService {

    @Resource
    ObjDataHisMapper objDataHisMapper;

    public List<ObjDataHis> selectRecentTwoRecords(ObjDataHisQo qo) {
        return objDataHisMapper.selectRecentTwoRecords(qo);
    }
}
