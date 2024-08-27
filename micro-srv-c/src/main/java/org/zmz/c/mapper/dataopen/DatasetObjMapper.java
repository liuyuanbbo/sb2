package org.zmz.c.mapper.dataopen;

import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;

import java.util.List;
import java.util.Map;

/**
 * @author Zmz
 */
public interface DatasetObjMapper {
    List<ObjKeyTableRelaVo> selectObjKeyTableColumnRel(Map<String, Object> params);
}