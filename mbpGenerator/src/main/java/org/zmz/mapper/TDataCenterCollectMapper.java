package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.TDataCenterCollect;
import org.zmz.model.TDataCenterCollectExample;

public interface TDataCenterCollectMapper {
    long countByExample(TDataCenterCollectExample example);

    int deleteByExample(TDataCenterCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDataCenterCollect row);

    int insertSelective(TDataCenterCollect row);

    List<TDataCenterCollect> selectByExample(TDataCenterCollectExample example);

    TDataCenterCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TDataCenterCollect row, @Param("example") TDataCenterCollectExample example);

    int updateByExample(@Param("row") TDataCenterCollect row, @Param("example") TDataCenterCollectExample example);

    int updateByPrimaryKeySelective(TDataCenterCollect row);

    int updateByPrimaryKey(TDataCenterCollect row);
}