package org.zmz.d.mockito.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmz.d.mockito.dao.UserMockDao;
import org.zmz.d.mockito.model.MockUser;

/**
 * @author Zmz
 */
@Service
public class UserMockService {

    @Resource
    UserMockDao userMockDao;

    public MockUser getById(Long id) {
        return userMockDao.getById(id);
    }

}
