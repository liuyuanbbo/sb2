package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.SServiceApply;
import org.zmz.model.SServiceApplyExample;

public interface SServiceApplyMapper {
    long countByExample(SServiceApplyExample example);

    int deleteByExample(SServiceApplyExample example);

    int deleteByPrimaryKey(Long applyId);

    int insert(SServiceApply row);

    int insertSelective(SServiceApply row);

    List<SServiceApply> selectByExampleWithBLOBs(SServiceApplyExample example);

    List<SServiceApply> selectByExample(SServiceApplyExample example);

    SServiceApply selectByPrimaryKey(Long applyId);

    int updateByExampleSelective(@Param("row") SServiceApply row, @Param("example") SServiceApplyExample example);

    int updateByExampleWithBLOBs(@Param("row") SServiceApply row, @Param("example") SServiceApplyExample example);

    int updateByExample(@Param("row") SServiceApply row, @Param("example") SServiceApplyExample example);

    int updateByPrimaryKeySelective(SServiceApply row);

    int updateByPrimaryKeyWithBLOBs(SServiceApply row);

    int updateByPrimaryKey(SServiceApply row);
}