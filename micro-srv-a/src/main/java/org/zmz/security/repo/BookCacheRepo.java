package org.zmz.security.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.zmz.security.model.Book;

@Repository
public class BookCacheRepo {

    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Book findById(Long id) {
        return (Book) redisTemplate.opsForValue().get("id");
    }
}
