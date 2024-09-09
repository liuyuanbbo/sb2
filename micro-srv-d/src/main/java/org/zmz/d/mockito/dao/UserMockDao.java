package org.zmz.d.mockito.dao;

import org.springframework.stereotype.Repository;
import org.zmz.d.mockito.model.MockUser;

/**
 * @author Zmz
 */
@Repository
public interface UserMockDao {

    MockUser getById(Long id);

}
