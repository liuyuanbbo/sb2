package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.TDataCenterRule;
import org.zmz.model.TDataCenterRuleExample;

public interface TDataCenterRuleMapper {
    long countByExample(TDataCenterRuleExample example);

    int deleteByExample(TDataCenterRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDataCenterRule row);

    int insertSelective(TDataCenterRule row);

    List<TDataCenterRule> selectByExample(TDataCenterRuleExample example);

    TDataCenterRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TDataCenterRule row, @Param("example") TDataCenterRuleExample example);

    int updateByExample(@Param("row") TDataCenterRule row, @Param("example") TDataCenterRuleExample example);

    int updateByPrimaryKeySelective(TDataCenterRule row);

    int updateByPrimaryKey(TDataCenterRule row);
}