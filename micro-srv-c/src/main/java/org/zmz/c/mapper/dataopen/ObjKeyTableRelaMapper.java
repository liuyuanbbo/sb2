package org.zmz.c.mapper.dataopen;

import org.zmz.c.qo.dataopen.ObjKeyTableRelaQo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;

import java.util.List;

public interface ObjKeyTableRelaMapper {
    /**
     * 查询派生指标统计粒度，包含关联字段
     */
    List<ObjKeyTableRelaVo> selectKeyRelaByQo(ObjKeyTableRelaQo params);
}