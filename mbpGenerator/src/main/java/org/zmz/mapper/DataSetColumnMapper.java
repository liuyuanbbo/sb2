package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DataSetColumn;
import org.zmz.model.DataSetColumnExample;

public interface DataSetColumnMapper {
    long countByExample(DataSetColumnExample example);

    int deleteByExample(DataSetColumnExample example);

    int deleteByPrimaryKey(Long appDetailId);

    int insert(DataSetColumn row);

    int insertSelective(DataSetColumn row);

    List<DataSetColumn> selectByExampleWithBLOBs(DataSetColumnExample example);

    List<DataSetColumn> selectByExample(DataSetColumnExample example);

    DataSetColumn selectByPrimaryKey(Long appDetailId);

    int updateByExampleSelective(@Param("row") DataSetColumn row, @Param("example") DataSetColumnExample example);

    int updateByExampleWithBLOBs(@Param("row") DataSetColumn row, @Param("example") DataSetColumnExample example);

    int updateByExample(@Param("row") DataSetColumn row, @Param("example") DataSetColumnExample example);

    int updateByPrimaryKeySelective(DataSetColumn row);

    int updateByPrimaryKeyWithBLOBs(DataSetColumn row);

    int updateByPrimaryKey(DataSetColumn row);
}