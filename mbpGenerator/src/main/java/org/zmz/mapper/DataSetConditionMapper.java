package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DataSetCondition;
import org.zmz.model.DataSetConditionExample;

public interface DataSetConditionMapper {
    long countByExample(DataSetConditionExample example);

    int deleteByExample(DataSetConditionExample example);

    int deleteByPrimaryKey(Long condId);

    int insert(DataSetCondition row);

    int insertSelective(DataSetCondition row);

    List<DataSetCondition> selectByExampleWithBLOBs(DataSetConditionExample example);

    List<DataSetCondition> selectByExample(DataSetConditionExample example);

    DataSetCondition selectByPrimaryKey(Long condId);

    int updateByExampleSelective(@Param("row") DataSetCondition row, @Param("example") DataSetConditionExample example);

    int updateByExampleWithBLOBs(@Param("row") DataSetCondition row, @Param("example") DataSetConditionExample example);

    int updateByExample(@Param("row") DataSetCondition row, @Param("example") DataSetConditionExample example);

    int updateByPrimaryKeySelective(DataSetCondition row);

    int updateByPrimaryKeyWithBLOBs(DataSetCondition row);

    int updateByPrimaryKey(DataSetCondition row);
}