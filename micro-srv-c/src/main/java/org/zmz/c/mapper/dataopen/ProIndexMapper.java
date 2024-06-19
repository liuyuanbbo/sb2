package org.zmz.c.mapper.dataopen;

import org.zmz.c.pojo.dataopen.ProIndex;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDateTime;

public interface ProIndexMapper extends Mapper<ProIndex> {
    LocalDateTime selectNow();
}
