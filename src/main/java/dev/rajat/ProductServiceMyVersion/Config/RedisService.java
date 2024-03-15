package dev.rajat.ProductServiceMyVersion.Config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> template;

    public RedisService(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public void setData(String key, Object value){
        template.opsForValue().set(key, value);
    }

    public Object getData(String key){
       return template.opsForValue().get(key);
    }
}
