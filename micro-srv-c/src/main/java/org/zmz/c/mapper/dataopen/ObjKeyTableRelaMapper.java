package org.zmz.c.mapper.dataopen;

import java.util.List;

import org.zmz.c.mapper.TkAndMPlusMapper;
import org.zmz.c.pojo.dataopen.ObjKeyTableRela;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaQo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;

/**
 * @author Zmz
 */
public interface ObjKeyTableRelaMapper extends TkAndMPlusMapper<ObjKeyTableRela> {
    /**
     * 查询派生指标统计粒度，包含关联字段
     */
    List<ObjKeyTableRelaVo> selectKeyRelaByQo(ObjKeyTableRelaQo params);
}