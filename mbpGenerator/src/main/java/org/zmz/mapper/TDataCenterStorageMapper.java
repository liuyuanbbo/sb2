package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.TDataCenterStorage;
import org.zmz.model.TDataCenterStorageExample;

public interface TDataCenterStorageMapper {
    long countByExample(TDataCenterStorageExample example);

    int deleteByExample(TDataCenterStorageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDataCenterStorage row);

    int insertSelective(TDataCenterStorage row);

    List<TDataCenterStorage> selectByExample(TDataCenterStorageExample example);

    TDataCenterStorage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TDataCenterStorage row, @Param("example") TDataCenterStorageExample example);

    int updateByExample(@Param("row") TDataCenterStorage row, @Param("example") TDataCenterStorageExample example);

    int updateByPrimaryKeySelective(TDataCenterStorage row);

    int updateByPrimaryKey(TDataCenterStorage row);
}