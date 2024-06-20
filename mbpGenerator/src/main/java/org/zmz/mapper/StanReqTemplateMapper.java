package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.StanReqTemplate;
import org.zmz.model.StanReqTemplateExample;

public interface StanReqTemplateMapper {
    long countByExample(StanReqTemplateExample example);

    int deleteByExample(StanReqTemplateExample example);

    int deleteByPrimaryKey(Long templateId);

    int insert(StanReqTemplate row);

    int insertSelective(StanReqTemplate row);

    List<StanReqTemplate> selectByExample(StanReqTemplateExample example);

    StanReqTemplate selectByPrimaryKey(Long templateId);

    int updateByExampleSelective(@Param("row") StanReqTemplate row, @Param("example") StanReqTemplateExample example);

    int updateByExample(@Param("row") StanReqTemplate row, @Param("example") StanReqTemplateExample example);

    int updateByPrimaryKeySelective(StanReqTemplate row);

    int updateByPrimaryKey(StanReqTemplate row);
}