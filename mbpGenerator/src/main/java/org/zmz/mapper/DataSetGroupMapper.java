package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DataSetGroup;
import org.zmz.model.DataSetGroupExample;

public interface DataSetGroupMapper {
    long countByExample(DataSetGroupExample example);

    int deleteByExample(DataSetGroupExample example);

    int deleteByPrimaryKey(Long groupId);

    int insert(DataSetGroup row);

    int insertSelective(DataSetGroup row);

    List<DataSetGroup> selectByExample(DataSetGroupExample example);

    DataSetGroup selectByPrimaryKey(Long groupId);

    int updateByExampleSelective(@Param("row") DataSetGroup row, @Param("example") DataSetGroupExample example);

    int updateByExample(@Param("row") DataSetGroup row, @Param("example") DataSetGroupExample example);

    int updateByPrimaryKeySelective(DataSetGroup row);

    int updateByPrimaryKey(DataSetGroup row);
}