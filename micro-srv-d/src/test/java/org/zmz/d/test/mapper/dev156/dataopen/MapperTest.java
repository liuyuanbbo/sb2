package org.zmz.d.test.mapper.dev156.dataopen;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.d.mapper.dev156.dataopen.TestObjInfoMapper;
import org.zmz.d.pojo.dev156.dataopen.ObjInfo;

import java.util.List;

@SpringBootTest
@Slf4j
public class MapperTest {

    @Autowired
    TestObjInfoMapper testObjInfoMapper;

    @Test
    public void t1() {
        ObjInfo objInfo = testObjInfoMapper.selectById(2342L);
        log.info("{}", ReflectionToStringBuilder.toString(objInfo, ToStringStyle.MULTI_LINE_STYLE));
    }

    @Test
    public void t2() {
        List<ObjInfo> objInfos = testObjInfoMapper.selectList(null);
        for (ObjInfo objInfo : objInfos) {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            log.info("{}", ReflectionToStringBuilder.toString(objInfo, ToStringStyle.MULTI_LINE_STYLE));
            log.info("====================================================");
        }
    }
}
