package org.zmz.security.mapper.dataopen;

import org.zmz.security.model.ObjDataHis;
import org.zmz.security.vo.request.ObjDataHisQo;

import java.util.List;

public interface ObjDataHisMapper {
    List<ObjDataHis> selectRecentTwoRecords(ObjDataHisQo qo);

    List<ObjDataHis> listAll();
}
