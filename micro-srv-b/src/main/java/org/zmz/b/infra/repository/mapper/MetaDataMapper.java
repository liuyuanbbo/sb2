package org.zmz.b.infra.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zmz.b.infra.repository.model.MetaData;

import java.time.LocalDateTime;

public interface MetaDataMapper extends BaseMapper<MetaData> {
    LocalDateTime selectNow();
}
