package org.zmz.c.mapper.dataopen;

import org.apache.ibatis.annotations.Param;
import org.zmz.c.mapper.TkAndMPlusMapper;
import org.zmz.c.pojo.dataopen.ObjColumnRela;

/**
 * @author Zmz
 */
public interface ObjColumnRelaMapper extends TkAndMPlusMapper<ObjColumnRela> {
    ObjColumnRela getObjColumnByCondition(@Param("objectId") Long objectId, @Param("srcTableId") Long srcTableId,
        @Param("columnCode") String columnCode);
}