package org.zmz.b.infra.repository.service;

import org.springframework.stereotype.Service;
import org.zmz.b.infra.repository.mapper.CollectMapper;

import javax.annotation.Resource;

@Service
public class CollectService {
    @Resource
    CollectMapper collectMapper;
}
