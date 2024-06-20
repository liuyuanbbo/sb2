package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.StanReqTemplateGroup;
import org.zmz.model.StanReqTemplateGroupExample;

public interface StanReqTemplateGroupMapper {
    long countByExample(StanReqTemplateGroupExample example);

    int deleteByExample(StanReqTemplateGroupExample example);

    int deleteByPrimaryKey(Long templateGroupId);

    int insert(StanReqTemplateGroup row);

    int insertSelective(StanReqTemplateGroup row);

    List<StanReqTemplateGroup> selectByExample(StanReqTemplateGroupExample example);

    StanReqTemplateGroup selectByPrimaryKey(Long templateGroupId);

    int updateByExampleSelective(@Param("row") StanReqTemplateGroup row, @Param("example") StanReqTemplateGroupExample example);

    int updateByExample(@Param("row") StanReqTemplateGroup row, @Param("example") StanReqTemplateGroupExample example);

    int updateByPrimaryKeySelective(StanReqTemplateGroup row);

    int updateByPrimaryKey(StanReqTemplateGroup row);
}