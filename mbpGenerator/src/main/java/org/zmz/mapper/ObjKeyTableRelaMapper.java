package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.ObjKeyTableRela;
import org.zmz.model.ObjKeyTableRelaExample;

public interface ObjKeyTableRelaMapper {
    long countByExample(ObjKeyTableRelaExample example);

    int deleteByExample(ObjKeyTableRelaExample example);

    int deleteByPrimaryKey(Long relaId);

    int insert(ObjKeyTableRela row);

    int insertSelective(ObjKeyTableRela row);

    List<ObjKeyTableRela> selectByExample(ObjKeyTableRelaExample example);

    ObjKeyTableRela selectByPrimaryKey(Long relaId);

    int updateByExampleSelective(@Param("row") ObjKeyTableRela row, @Param("example") ObjKeyTableRelaExample example);

    int updateByExample(@Param("row") ObjKeyTableRela row, @Param("example") ObjKeyTableRelaExample example);

    int updateByPrimaryKeySelective(ObjKeyTableRela row);

    int updateByPrimaryKey(ObjKeyTableRela row);
}