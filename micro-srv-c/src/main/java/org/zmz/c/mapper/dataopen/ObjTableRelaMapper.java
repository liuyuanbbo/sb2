package org.zmz.c.mapper.dataopen;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zmz.c.pojo.dataopen.ObjTableRela;

public interface ObjTableRelaMapper {
    List<ObjTableRela> getObjTableRelaList(Map<?, ?> params);

    List<ObjTableRela> getObjTableRela(Set<Long> tableIds);
}