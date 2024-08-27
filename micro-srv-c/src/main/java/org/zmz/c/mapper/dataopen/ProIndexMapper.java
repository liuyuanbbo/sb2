package org.zmz.c.mapper.dataopen;

import java.time.LocalDateTime;

import org.zmz.c.mapper.TkAndMPlusMapper;
import org.zmz.c.pojo.dataopen.ProIndex;

/**
 * @author Zmz
 */
public interface ProIndexMapper extends TkAndMPlusMapper<ProIndex> {
    LocalDateTime selectNow();
}
