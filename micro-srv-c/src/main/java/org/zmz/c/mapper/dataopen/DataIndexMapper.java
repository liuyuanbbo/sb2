package org.zmz.c.mapper.dataopen;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zmz.c.dto.dataopen.IndexDto;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaQo;
import org.zmz.c.vo.dataopen.dataindex.DimIndexDataGrpVo;
import org.zmz.c.vo.dataopen.dataindex.DimIndexVo;
import org.zmz.c.vo.dataopen.dataindex.ObjDimDataGrpVo;
import org.zmz.c.vo.dataopen.dataindex.ObjKeyTableColumnVo;
import org.zmz.c.vo.dataopen.dataindex.ObjectDimVo;

/**
 * @author Zmz
 */
public interface DataIndexMapper {
    /**
     * 根据选择对象查询指标
     *
     * @param params 入参
     * @return Set
     */
    Set<IndexDto> unionAllProIndexAndDimIndex(Map<String, Object> params);

    /**
     * 查询对象目录
     *
     * @param params 入参
     * @return List
     */
    List<ObjDimDataGrpVo> queryObjDimDataGrp(Map<String, Object> params);

    /**
     * 查询维度对象
     *
     * @param page 分页参数
     * @return List<ObjectDimVo>
     */
    List<ObjectDimVo> queryObjDimByPage(Map<String, Object> page);

    /**
     * 查询指标目录树
     *
     * @param params 入参
     * @return List<DimIndexDataGrpVo>
     */
    List<DimIndexDataGrpVo> queryDimIndexDataGrp(Map<String, Object> params);

    /**
     * 分页查询指标信息
     *
     * @param page 分页参数
     * @return List<DimIndexVo>
     */
    List<DimIndexVo> queryDimIndexByPage(Map<String, Object> page);

    /**
     * 查询表的关联关系
     *
     * @param objKeyTableRelaQo 入参
     * @return ObjKeyTableColumnVo
     */
    List<ObjKeyTableColumnVo> queryObjKeyTableRela(ObjKeyTableRelaQo objKeyTableRelaQo);
}
