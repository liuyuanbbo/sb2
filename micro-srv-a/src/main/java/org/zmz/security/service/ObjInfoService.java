package org.zmz.security.service;

import org.springframework.stereotype.Service;
import org.zmz.security.mapper.dataopen.ObjInfoMapper;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class ObjInfoService {

    @Resource
    ObjInfoMapper objInfoMapper;

    public LocalDateTime selectNow() {
        return objInfoMapper.selectNow();
    }

}
