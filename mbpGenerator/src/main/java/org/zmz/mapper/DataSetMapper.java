package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DataSet;
import org.zmz.model.DataSetExample;
import org.zmz.model.DataSetWithBLOBs;

public interface DataSetMapper {
    long countByExample(DataSetExample example);

    int deleteByExample(DataSetExample example);

    int deleteByPrimaryKey(Long appId);

    int insert(DataSetWithBLOBs row);

    int insertSelective(DataSetWithBLOBs row);

    List<DataSetWithBLOBs> selectByExampleWithBLOBs(DataSetExample example);

    List<DataSet> selectByExample(DataSetExample example);

    DataSetWithBLOBs selectByPrimaryKey(Long appId);

    int updateByExampleSelective(@Param("row") DataSetWithBLOBs row, @Param("example") DataSetExample example);

    int updateByExampleWithBLOBs(@Param("row") DataSetWithBLOBs row, @Param("example") DataSetExample example);

    int updateByExample(@Param("row") DataSet row, @Param("example") DataSetExample example);

    int updateByPrimaryKeySelective(DataSetWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(DataSetWithBLOBs row);

    int updateByPrimaryKey(DataSet row);
}