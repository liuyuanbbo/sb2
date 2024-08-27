package org.zmz.c.mapper.dataopen;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zmz.c.pojo.dataopen.AttrValue;

/**
 * @author Zmz
 */
public interface StaticDataMapper {
    /**
     * 根据code过虑AttrValue表数据
     *
     * @param code 编码
     * @return List<AttrValue>
     */
    List<AttrValue> getAttrValueByCode(@Param("code") String code, @Param("language") String language);

    /**
     * 根据编码获取执行的dcSql
     */
    String getStaticAttrDcSqlByCode(@Param("code") String code);
}