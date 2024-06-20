package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.AttrValue;
import org.zmz.model.AttrValueExample;

public interface AttrValueMapper {
    long countByExample(AttrValueExample example);

    int deleteByExample(AttrValueExample example);

    int deleteByPrimaryKey(Long attrValueId);

    int insert(AttrValue row);

    int insertSelective(AttrValue row);

    List<AttrValue> selectByExampleWithBLOBs(AttrValueExample example);

    List<AttrValue> selectByExample(AttrValueExample example);

    AttrValue selectByPrimaryKey(Long attrValueId);

    int updateByExampleSelective(@Param("row") AttrValue row, @Param("example") AttrValueExample example);

    int updateByExampleWithBLOBs(@Param("row") AttrValue row, @Param("example") AttrValueExample example);

    int updateByExample(@Param("row") AttrValue row, @Param("example") AttrValueExample example);

    int updateByPrimaryKeySelective(AttrValue row);

    int updateByPrimaryKeyWithBLOBs(AttrValue row);

    int updateByPrimaryKey(AttrValue row);
}