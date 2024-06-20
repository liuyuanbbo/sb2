package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DimIndexParam;
import org.zmz.model.DimIndexParamExample;

public interface DimIndexParamMapper {
    long countByExample(DimIndexParamExample example);

    int deleteByExample(DimIndexParamExample example);

    int deleteByPrimaryKey(Long paramId);

    int insert(DimIndexParam row);

    int insertSelective(DimIndexParam row);

    List<DimIndexParam> selectByExample(DimIndexParamExample example);

    DimIndexParam selectByPrimaryKey(Long paramId);

    int updateByExampleSelective(@Param("row") DimIndexParam row, @Param("example") DimIndexParamExample example);

    int updateByExample(@Param("row") DimIndexParam row, @Param("example") DimIndexParamExample example);

    int updateByPrimaryKeySelective(DimIndexParam row);

    int updateByPrimaryKey(DimIndexParam row);
}