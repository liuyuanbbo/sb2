package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.TDataCenterOrgan;
import org.zmz.model.TDataCenterOrganExample;

public interface TDataCenterOrganMapper {
    long countByExample(TDataCenterOrganExample example);

    int deleteByExample(TDataCenterOrganExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDataCenterOrgan row);

    int insertSelective(TDataCenterOrgan row);

    List<TDataCenterOrgan> selectByExample(TDataCenterOrganExample example);

    TDataCenterOrgan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TDataCenterOrgan row, @Param("example") TDataCenterOrganExample example);

    int updateByExample(@Param("row") TDataCenterOrgan row, @Param("example") TDataCenterOrganExample example);

    int updateByPrimaryKeySelective(TDataCenterOrgan row);

    int updateByPrimaryKey(TDataCenterOrgan row);
}