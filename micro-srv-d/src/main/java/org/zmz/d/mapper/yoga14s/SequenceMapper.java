package org.zmz.d.mapper.yoga14s;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("yoga14sSequenceMapper")
public interface SequenceMapper {
    Long nextVal(@Param("seqName") String seqName);
}
