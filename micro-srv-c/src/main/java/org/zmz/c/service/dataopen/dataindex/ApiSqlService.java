package org.zmz.c.service.dataopen.dataindex;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

/**
 * @author Zmz
 */
@Service
public class ApiSqlService {

    public String parseSqlApiParam(Map<String, Object> param) {
        String sql = MapUtils.getString(param, "sql");
        Integer datasourceId = MapUtils.getInteger(param, "datasourceId");
        return null;
    }
}
