package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DataSetAnalysis;
import org.zmz.model.DataSetAnalysisExample;

public interface DataSetAnalysisMapper {
    long countByExample(DataSetAnalysisExample example);

    int deleteByExample(DataSetAnalysisExample example);

    int deleteByPrimaryKey(Long analysisId);

    int insert(DataSetAnalysis row);

    int insertSelective(DataSetAnalysis row);

    List<DataSetAnalysis> selectByExample(DataSetAnalysisExample example);

    DataSetAnalysis selectByPrimaryKey(Long analysisId);

    int updateByExampleSelective(@Param("row") DataSetAnalysis row, @Param("example") DataSetAnalysisExample example);

    int updateByExample(@Param("row") DataSetAnalysis row, @Param("example") DataSetAnalysisExample example);

    int updateByPrimaryKeySelective(DataSetAnalysis row);

    int updateByPrimaryKey(DataSetAnalysis row);
}