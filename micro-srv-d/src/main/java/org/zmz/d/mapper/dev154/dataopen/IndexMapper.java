package org.zmz.d.mapper.dev154.dataopen;

import org.zmz.d.pojo.dev154.dto.IndexDto;

import java.util.Set;

public interface IndexMapper {
    Set<IndexDto> unionAllProIndexAndDimIndex();
}
