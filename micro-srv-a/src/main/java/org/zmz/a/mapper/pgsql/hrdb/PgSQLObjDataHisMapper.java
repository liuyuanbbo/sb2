package org.zmz.a.mapper.pgsql.hrdb;

import org.apache.ibatis.annotations.Param;
import org.zmz.a.model.dataopen.ObjDataHis;

import java.util.List;

public interface PgSQLObjDataHisMapper {
    void batchInsert(@Param("objDataHisList") List<ObjDataHis> objDataHisList);
}
