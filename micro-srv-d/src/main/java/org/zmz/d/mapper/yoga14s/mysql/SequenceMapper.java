package org.zmz.d.mapper.yoga14s.mysql;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("yoga14sMysqlSequenceMapper")
public interface SequenceMapper {
    Long nextVal(@Param("seqName") String seqName);
}
