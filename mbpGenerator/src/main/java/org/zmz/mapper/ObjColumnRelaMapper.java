package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.ObjColumnRela;
import org.zmz.model.ObjColumnRelaExample;

public interface ObjColumnRelaMapper {
    long countByExample(ObjColumnRelaExample example);

    int deleteByExample(ObjColumnRelaExample example);

    int deleteByPrimaryKey(Long relaId);

    int insert(ObjColumnRela row);

    int insertSelective(ObjColumnRela row);

    List<ObjColumnRela> selectByExample(ObjColumnRelaExample example);

    ObjColumnRela selectByPrimaryKey(Long relaId);

    int updateByExampleSelective(@Param("row") ObjColumnRela row, @Param("example") ObjColumnRelaExample example);

    int updateByExample(@Param("row") ObjColumnRela row, @Param("example") ObjColumnRelaExample example);

    int updateByPrimaryKeySelective(ObjColumnRela row);

    int updateByPrimaryKey(ObjColumnRela row);
}