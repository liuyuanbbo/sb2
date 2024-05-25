package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.TDataCenterCalculate;
import org.zmz.model.TDataCenterCalculateExample;

public interface TDataCenterCalculateMapper {
    long countByExample(TDataCenterCalculateExample example);

    int deleteByExample(TDataCenterCalculateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDataCenterCalculate row);

    int insertSelective(TDataCenterCalculate row);

    List<TDataCenterCalculate> selectByExample(TDataCenterCalculateExample example);

    TDataCenterCalculate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TDataCenterCalculate row, @Param("example") TDataCenterCalculateExample example);

    int updateByExample(@Param("row") TDataCenterCalculate row, @Param("example") TDataCenterCalculateExample example);

    int updateByPrimaryKeySelective(TDataCenterCalculate row);

    int updateByPrimaryKey(TDataCenterCalculate row);
}