package org.zmz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zmz.model.DopUserFavorLog;
import org.zmz.model.DopUserFavorLogExample;

public interface DopUserFavorLogMapper {
    long countByExample(DopUserFavorLogExample example);

    int deleteByExample(DopUserFavorLogExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(DopUserFavorLog row);

    int insertSelective(DopUserFavorLog row);

    List<DopUserFavorLog> selectByExample(DopUserFavorLogExample example);

    DopUserFavorLog selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("row") DopUserFavorLog row, @Param("example") DopUserFavorLogExample example);

    int updateByExample(@Param("row") DopUserFavorLog row, @Param("example") DopUserFavorLogExample example);

    int updateByPrimaryKeySelective(DopUserFavorLog row);

    int updateByPrimaryKey(DopUserFavorLog row);
}