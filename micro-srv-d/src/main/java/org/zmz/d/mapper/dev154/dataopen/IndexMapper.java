package org.zmz.d.mapper.dev154.dataopen;

import java.util.Set;

import org.zmz.d.pojo.dev154.dto.IndexDto;
import org.zmz.d.pojo.dev154.dto.ObjInfoDto;

/**
 * @author Zmz
 */
public interface IndexMapper {
    Set<ObjInfoDto> selectObjInfo(Set<Long> objectIds);
    Set<IndexDto> unionAllProIndexAndDimIndex(Set<Long> objectIds);
}
