package org.zmz.d.service.dev154.dataportal;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;
import org.zmz.d.mapper.dev154.dataportal.StanTagMapper;
import org.zmz.d.pojo.dev154.dataportal.StanTag;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * @author Zmz
 */
@Service
public class StanTagService {

    static final Cache<String, List<StanTag>> STAN_TAG_CACHE = Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(1))
            .maximumSize(1000).build();

    @Resource
    StanTagMapper stanTagMapper;

    public Map<String, List<StanTag>> getStanTagCache() {
        return STAN_TAG_CACHE.asMap();
    }

    public List<StanTag> findAll() {
        return STAN_TAG_CACHE.get("stanTags", key -> stanTagMapper.selectList(null));
    }

}
