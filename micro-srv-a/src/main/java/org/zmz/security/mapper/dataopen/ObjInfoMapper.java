package org.zmz.security.mapper.dataopen;

import org.zmz.security.model.ObjInfo;
import org.zmz.security.vo.request.ObjInfoQueryQo;

import java.time.LocalDateTime;
import java.util.List;

public interface ObjInfoMapper {
    LocalDateTime selectNow();

    List<ObjInfo> queryConditionByChoose(ObjInfoQueryQo objInfoQueryQo);

    List<ObjInfo> queryCondition(ObjInfoQueryQo objInfoQueryQo);

    List<ObjInfo> selectAll();
}
