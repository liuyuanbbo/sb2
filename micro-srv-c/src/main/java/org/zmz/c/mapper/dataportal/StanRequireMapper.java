package org.zmz.c.mapper.dataportal;

import org.zmz.c.pojo.dataportal.StanRequire;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDateTime;

public interface StanRequireMapper extends Mapper<StanRequire> {
    LocalDateTime selectNow();
}
