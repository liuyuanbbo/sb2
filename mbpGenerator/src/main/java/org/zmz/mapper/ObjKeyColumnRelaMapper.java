package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.ObjKeyColumnRela;
import org.zmz.model.ObjKeyColumnRelaExample;

public interface ObjKeyColumnRelaMapper {
    long countByExample(ObjKeyColumnRelaExample example);

    int deleteByExample(ObjKeyColumnRelaExample example);

    int deleteByPrimaryKey(Long relaId);

    int insert(ObjKeyColumnRela row);

    int insertSelective(ObjKeyColumnRela row);

    List<ObjKeyColumnRela> selectByExample(ObjKeyColumnRelaExample example);

    ObjKeyColumnRela selectByPrimaryKey(Long relaId);

    int updateByExampleSelective(@Param("row") ObjKeyColumnRela row, @Param("example") ObjKeyColumnRelaExample example);

    int updateByExample(@Param("row") ObjKeyColumnRela row, @Param("example") ObjKeyColumnRelaExample example);

    int updateByPrimaryKeySelective(ObjKeyColumnRela row);

    int updateByPrimaryKey(ObjKeyColumnRela row);
}