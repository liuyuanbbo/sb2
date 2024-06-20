package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.StanReqTemplateField;
import org.zmz.model.StanReqTemplateFieldExample;

public interface StanReqTemplateFieldMapper {
    long countByExample(StanReqTemplateFieldExample example);

    int deleteByExample(StanReqTemplateFieldExample example);

    int deleteByPrimaryKey(Long templateFieldId);

    int insert(StanReqTemplateField row);

    int insertSelective(StanReqTemplateField row);

    List<StanReqTemplateField> selectByExample(StanReqTemplateFieldExample example);

    StanReqTemplateField selectByPrimaryKey(Long templateFieldId);

    int updateByExampleSelective(@Param("row") StanReqTemplateField row, @Param("example") StanReqTemplateFieldExample example);

    int updateByExample(@Param("row") StanReqTemplateField row, @Param("example") StanReqTemplateFieldExample example);

    int updateByPrimaryKeySelective(StanReqTemplateField row);

    int updateByPrimaryKey(StanReqTemplateField row);
}