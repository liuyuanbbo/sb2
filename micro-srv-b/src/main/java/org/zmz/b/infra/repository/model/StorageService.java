package org.zmz.b.infra.repository.model;

import org.springframework.stereotype.Service;
import org.zmz.b.infra.repository.mapper.StorageMapper;

import javax.annotation.Resource;

@Service
public class StorageService {

    @Resource
    StorageMapper storageMapper;
}
