package org.zmz.c.mapper.dataportal;

import java.time.LocalDateTime;

import org.zmz.c.mapper.TkAndMPlusMapper;
import org.zmz.c.pojo.dataportal.StanRequire;

/**
 * @author Zmz
 */
public interface StanRequireMapper extends TkAndMPlusMapper<StanRequire> {
    LocalDateTime selectNow();
}
