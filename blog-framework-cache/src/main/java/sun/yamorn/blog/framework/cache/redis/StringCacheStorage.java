package sun.yamorn.blog.framework.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sun.yamorn.blog.framework.cache.util.JsonSerializeUtils;

import java.util.Map;

/**
 * Created by root on 2015/10/29.
 *
 * Redis Cache Implementation.
 */
@Component
public class StringCacheStorage implements ICacheStorage<String, Object> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public void set(final String key, final Object value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                String v = JsonSerializeUtils.toJsonString(value);
                connection.set(key.getBytes(DEFAULT_CHARSET), v.getBytes(DEFAULT_CHARSET));
                return null;
            }
        });
    }

    @Override
    public void setEx(final String key, final Object value, final long expire) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                String v = JsonSerializeUtils.toJsonString(value);
                connection.setEx(key.getBytes(DEFAULT_CHARSET), expire, v.getBytes(DEFAULT_CHARSET));
                return null;
            }
        });
    }

    @Override
    public void setNx(final String key, final Object value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                String v = JsonSerializeUtils.toJsonString(value);
                connection.setNX(key.getBytes(DEFAULT_CHARSET), v.getBytes(DEFAULT_CHARSET));
                return null;
            }
        });
    }

    @Override
    public void mSet(final Map<String, Object> values) {
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (Map.Entry<String, Object> entry : values.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    String v = JsonSerializeUtils.toJsonString(value);
                    connection.set(key.getBytes(DEFAULT_CHARSET), v.getBytes(DEFAULT_CHARSET));
                }
                return null;
            }
        });
    }

    @Override
    public void mSetNX(final Map<String, Object> values) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (Map.Entry<String, Object> entry : values.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    String v = JsonSerializeUtils.toJsonString(value);
                    connection.setNX(key.getBytes(DEFAULT_CHARSET), v.getBytes(DEFAULT_CHARSET));
                }
                return null;
            }
        });
    }

    @Override
    public <V> V get(final String key, final Class<V> clazz) {
        String value = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] result = connection.get(key.getBytes(DEFAULT_CHARSET));
                if (result != null) {
                    return new String(result, DEFAULT_CHARSET);
                }
                return null;
            }
        });
        return JsonSerializeUtils.toObject(value, clazz);
    }

    @Override
    public Long del(final String... keys) {
        final long num = 0;
        redisTemplate.executePipelined(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long num = 0;
                for (String key : keys) {
                    num += connection.del(key.getBytes(DEFAULT_CHARSET));
                }
                return num;
            }
        });
        return num;
    }

    @Override
    public Boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes(DEFAULT_CHARSET));
            }
        });
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
