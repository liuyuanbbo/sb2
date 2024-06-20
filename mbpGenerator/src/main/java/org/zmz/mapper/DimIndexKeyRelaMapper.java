package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DimIndexKeyRela;
import org.zmz.model.DimIndexKeyRelaExample;

public interface DimIndexKeyRelaMapper {
    long countByExample(DimIndexKeyRelaExample example);

    int deleteByExample(DimIndexKeyRelaExample example);

    int deleteByPrimaryKey(Long relaId);

    int insert(DimIndexKeyRela row);

    int insertSelective(DimIndexKeyRela row);

    List<DimIndexKeyRela> selectByExample(DimIndexKeyRelaExample example);

    DimIndexKeyRela selectByPrimaryKey(Long relaId);

    int updateByExampleSelective(@Param("row") DimIndexKeyRela row, @Param("example") DimIndexKeyRelaExample example);

    int updateByExample(@Param("row") DimIndexKeyRela row, @Param("example") DimIndexKeyRelaExample example);

    int updateByPrimaryKeySelective(DimIndexKeyRela row);

    int updateByPrimaryKey(DimIndexKeyRela row);
}