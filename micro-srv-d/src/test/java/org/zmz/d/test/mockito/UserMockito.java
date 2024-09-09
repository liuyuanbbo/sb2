package org.zmz.d.test.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.zmz.d.mockito.dao.UserMockDao;
import org.zmz.d.mockito.model.MockUser;
import org.zmz.d.mockito.service.UserMockService;

@SpringBootTest
@Slf4j
public class UserMockito {

    @Autowired
    UserMockService userMockService;

    @MockBean
    UserMockDao userMockDao;

    @Test
    public void t1() {
        Mockito.when(userMockDao.getById(1L)).thenReturn(new MockUser(1L, "n1", 20));

        MockUser mockUser = userMockService.getById(1L);

        Assertions.assertNotNull(mockUser);
        Assertions.assertEquals(mockUser.getName(), "n1");
        Assertions.assertEquals(mockUser.getId(), 1L);
    }

}
