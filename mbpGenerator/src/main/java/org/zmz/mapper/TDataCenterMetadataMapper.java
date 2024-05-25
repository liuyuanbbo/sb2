package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.TDataCenterMetadata;
import org.zmz.model.TDataCenterMetadataExample;

public interface TDataCenterMetadataMapper {
    long countByExample(TDataCenterMetadataExample example);

    int deleteByExample(TDataCenterMetadataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDataCenterMetadata row);

    int insertSelective(TDataCenterMetadata row);

    List<TDataCenterMetadata> selectByExample(TDataCenterMetadataExample example);

    TDataCenterMetadata selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TDataCenterMetadata row, @Param("example") TDataCenterMetadataExample example);

    int updateByExample(@Param("row") TDataCenterMetadata row, @Param("example") TDataCenterMetadataExample example);

    int updateByPrimaryKeySelective(TDataCenterMetadata row);

    int updateByPrimaryKey(TDataCenterMetadata row);
}