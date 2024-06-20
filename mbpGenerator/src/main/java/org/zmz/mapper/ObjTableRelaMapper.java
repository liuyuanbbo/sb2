package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.ObjTableRela;
import org.zmz.model.ObjTableRelaExample;

public interface ObjTableRelaMapper {
    long countByExample(ObjTableRelaExample example);

    int deleteByExample(ObjTableRelaExample example);

    int deleteByPrimaryKey(Long relaId);

    int insert(ObjTableRela row);

    int insertSelective(ObjTableRela row);

    List<ObjTableRela> selectByExample(ObjTableRelaExample example);

    ObjTableRela selectByPrimaryKey(Long relaId);

    int updateByExampleSelective(@Param("row") ObjTableRela row, @Param("example") ObjTableRelaExample example);

    int updateByExample(@Param("row") ObjTableRela row, @Param("example") ObjTableRelaExample example);

    int updateByPrimaryKeySelective(ObjTableRela row);

    int updateByPrimaryKey(ObjTableRela row);
}