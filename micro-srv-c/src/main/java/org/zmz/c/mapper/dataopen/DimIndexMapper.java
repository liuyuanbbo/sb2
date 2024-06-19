package org.zmz.c.mapper.dataopen;

import org.zmz.c.pojo.dataopen.DimIndex;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDateTime;

public interface DimIndexMapper extends Mapper<DimIndex> {
    LocalDateTime selectNow();
}
