package org.zmz.a.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zmz.a.mapper.dataopen.ObjDataHisMapper;
import org.zmz.a.mapper.pgsql.hrdb.PgSQLObjDataHisMapper;
import org.zmz.a.model.dataopen.ObjDataHis;
import org.zmz.a.vo.request.ObjDataHisQo;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ObjDataHisService {

    @Resource
    ObjDataHisMapper objDataHisMapper;

    @Resource
    PgSQLObjDataHisMapper pgSQLObjDataHisMapper;

    public List<ObjDataHis> selectRecentTwoRecords(ObjDataHisQo qo) {
        return objDataHisMapper.selectRecentTwoRecords(qo);
    }

    public void syncMysqlDataToPostgres() {
        List<ObjDataHis> objDataHisAll = objDataHisMapper.listAll();
        log.info("待同步数据量: {}", objDataHisAll.size());
        pgSQLObjDataHisMapper.batchInsert(objDataHisAll);
    }
}
