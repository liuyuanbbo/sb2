package org.zmz.c.mapper.dataopen;

import org.zmz.c.pojo.dataopen.DimIndexInfo;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDateTime;

public interface DimIndexInfoMapper extends Mapper<DimIndexInfo> {
    LocalDateTime selectNow();
}
