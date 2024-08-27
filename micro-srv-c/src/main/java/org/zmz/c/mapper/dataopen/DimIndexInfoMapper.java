package org.zmz.c.mapper.dataopen;

import java.time.LocalDateTime;

import org.zmz.c.mapper.TkAndMPlusMapper;
import org.zmz.c.pojo.dataopen.DimIndexInfo;

/**
 * @author Zmz
 */
public interface DimIndexInfoMapper extends TkAndMPlusMapper<DimIndexInfo> {
    LocalDateTime selectNow();
}
