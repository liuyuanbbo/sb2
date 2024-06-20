package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DataGrp;
import org.zmz.model.DataGrpExample;

public interface DataGrpMapper {
    long countByExample(DataGrpExample example);

    int deleteByExample(DataGrpExample example);

    int deleteByPrimaryKey(Long grpId);

    int insert(DataGrp row);

    int insertSelective(DataGrp row);

    List<DataGrp> selectByExample(DataGrpExample example);

    DataGrp selectByPrimaryKey(Long grpId);

    int updateByExampleSelective(@Param("row") DataGrp row, @Param("example") DataGrpExample example);

    int updateByExample(@Param("row") DataGrp row, @Param("example") DataGrpExample example);

    int updateByPrimaryKeySelective(DataGrp row);

    int updateByPrimaryKey(DataGrp row);
}