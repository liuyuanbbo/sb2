package org.zmz.a.mapper.dataopen;

import org.zmz.a.model.dataopen.ObjInfo;
import org.zmz.a.vo.request.ObjInfoQueryQo;

import java.time.LocalDateTime;
import java.util.List;

public interface ObjInfoMapper {
    LocalDateTime selectNow();

    List<ObjInfo> queryConditionByChoose(ObjInfoQueryQo objInfoQueryQo);

    List<ObjInfo> queryCondition(ObjInfoQueryQo objInfoQueryQo);

    List<ObjInfo> selectAll();
}
