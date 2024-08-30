package org.zmz.c.mapper.dataopen;

import java.util.List;
import java.util.Map;

import org.zmz.c.mapper.TkAndMPlusMapper;
import org.zmz.c.pojo.dataopen.ZmgrMetaColumns;
import org.zmz.c.vo.dataopen.dataindex.ZmgrMetaColumnsBaseVo;

/**
 * @author Zmz
 */
public interface ZmgrMetaColumnsMapper extends TkAndMPlusMapper<ZmgrMetaColumns> {
    List<ZmgrMetaColumnsBaseVo> selectListByCondition(Map<String, Object> qo);
}
