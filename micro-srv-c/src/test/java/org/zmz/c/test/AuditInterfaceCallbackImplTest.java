package org.zmz.c.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.c.service.DimensionService;

@SpringBootTest
public class AuditInterfaceCallbackImplTest {

    @Autowired
    DimensionService dimensionService;

    @Test
    public void test1() {
        dimensionService.changeStatus("123");
    }

}
