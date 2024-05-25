package org.zmz.b.infra.repository.service;

import org.springframework.stereotype.Service;
import org.zmz.b.infra.repository.mapper.CalculateMapper;

import javax.annotation.Resource;

@Service
public class CalculateService {

    @Resource
    CalculateMapper calculateMapper;

}
