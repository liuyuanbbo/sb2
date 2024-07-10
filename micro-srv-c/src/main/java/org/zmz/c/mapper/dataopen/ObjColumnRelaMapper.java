package org.zmz.c.mapper.dataopen;

import org.apache.ibatis.annotations.Param;
import org.zmz.c.pojo.dataopen.ObjColumnRela;
import tk.mybatis.mapper.common.Mapper;

public interface ObjColumnRelaMapper extends Mapper<ObjColumnRela> {
    ObjColumnRela getObjColumnByCondition(@Param("objectId") Long objectId, @Param("srcTableId") Long srcTableId, @Param("columnCode") String columnCode);
}