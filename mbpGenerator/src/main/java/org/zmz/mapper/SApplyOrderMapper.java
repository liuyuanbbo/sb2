package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.SApplyOrder;
import org.zmz.model.SApplyOrderExample;

public interface SApplyOrderMapper {
    long countByExample(SApplyOrderExample example);

    int deleteByExample(SApplyOrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(SApplyOrder row);

    int insertSelective(SApplyOrder row);

    List<SApplyOrder> selectByExample(SApplyOrderExample example);

    SApplyOrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("row") SApplyOrder row, @Param("example") SApplyOrderExample example);

    int updateByExample(@Param("row") SApplyOrder row, @Param("example") SApplyOrderExample example);

    int updateByPrimaryKeySelective(SApplyOrder row);

    int updateByPrimaryKey(SApplyOrder row);
}