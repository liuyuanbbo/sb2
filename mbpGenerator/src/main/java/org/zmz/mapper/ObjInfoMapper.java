package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.ObjInfo;
import org.zmz.model.ObjInfoExample;

public interface ObjInfoMapper {
    long countByExample(ObjInfoExample example);

    int deleteByExample(ObjInfoExample example);

    int deleteByPrimaryKey(Long objectId);

    int insert(ObjInfo row);

    int insertSelective(ObjInfo row);

    List<ObjInfo> selectByExample(ObjInfoExample example);

    ObjInfo selectByPrimaryKey(Long objectId);

    int updateByExampleSelective(@Param("row") ObjInfo row, @Param("example") ObjInfoExample example);

    int updateByExample(@Param("row") ObjInfo row, @Param("example") ObjInfoExample example);

    int updateByPrimaryKeySelective(ObjInfo row);

    int updateByPrimaryKey(ObjInfo row);
}