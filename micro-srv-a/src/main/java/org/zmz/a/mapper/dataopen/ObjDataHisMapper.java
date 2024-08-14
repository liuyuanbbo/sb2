package org.zmz.a.mapper.dataopen;

import org.zmz.a.model.dataopen.ObjDataHis;
import org.zmz.a.vo.request.ObjDataHisQo;

import java.util.List;

public interface ObjDataHisMapper {
    List<ObjDataHis> selectRecentTwoRecords(ObjDataHisQo qo);

    List<ObjDataHis> listAll();
}
