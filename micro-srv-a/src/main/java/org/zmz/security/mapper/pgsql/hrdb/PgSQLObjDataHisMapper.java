package org.zmz.security.mapper.pgsql.hrdb;

import org.apache.ibatis.annotations.Param;
import org.zmz.security.model.ObjDataHis;

import java.util.List;

public interface PgSQLObjDataHisMapper {
    void batchInsert(@Param("objDataHisList") List<ObjDataHis> objDataHisList);
}
