package org.zmz.b.infra.repository.service;

import org.springframework.stereotype.Service;
import org.zmz.b.infra.repository.mapper.code01.maria.MetaDataMapper;

import javax.annotation.Resource;

@Service
public class MetaDataService {
    @Resource
    MetaDataMapper metaDataMapper;
}
