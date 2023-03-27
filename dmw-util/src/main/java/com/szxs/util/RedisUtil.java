package com.szxs.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 锁定
     * @param key
     * @return
     */
    public boolean lock(String key){
        boolean flag = false;
        try {
            String lockKey = this.generateLockKey(key);
            flag = this.setNX(lockKey,"lock");
            if (flag){
                System.out.println(this.setTTL(lockKey,60));
            }
            return flag;
        }catch (Exception e){
            e.printStackTrace();
            return flag;
        }
    }

    /**
     * 释放锁
     * @param key
     */
    public void unlock(String key){
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        String lockKey = this.generateLockKey(key);
        RedisConnection connection = this.redisTemplate.getConnectionFactory().getConnection();
        connection.del(new byte[][]{lockKey.getBytes()});
        connection.close();
    }

    private String generateLockKey(String key){
        return String.format("LOCK:%s",key);
    }

    /**
     * 设置值
     * @param key
     * @param val
     * @return
     */
    public boolean setNX(String key,Object val){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        return ops.setIfAbsent(key, JSON.toJSONString(val));
    }
    /**
     * 设置值
     * @param key
     * @param val
     * @param seconds
     * @return
     */
    public boolean SetNX(String key,Object val,Integer seconds){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        return ops.setIfAbsent(key, JSON.toJSONString(val),seconds,TimeUnit.SECONDS);
    }




    /**
     * 增加有序zset值
     * @param key
     * @param val
     * @param score
     */
    public void zset(String key,String val,int score){
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ZSetOperations ops = redisTemplate.opsForZSet();
        ops.add(key,val,score);
    }

    /**
     * 获取有序zset值值
     * @param key
     * @param start
     * @param stop
     */
    public Set gset(String key, int start, int stop){
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ZSetOperations ops = redisTemplate.opsForZSet();
        return ops.rangeWithScores(key,start,stop);
    }

    /**
     * 测试传map
     * @param key
     * @param k1
     * @param k2
     * @param k3
     */
    public void Test(String key, String k1,  String k2,String k3){
        Map<String ,Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add(k1);
        set.add(k2);
        set.add(k3);
        map.put(key,set);
        this.Tset2(key, JSON.toJSONString(map),7*24*60*60);
    }

    public void Tset2(String key,String val,Integer time){
        set(key, val,TimeUnit.HOURS,time);
    }

    /**

     * 设置hash值
     * @param key
     * @param field
     * @param val
     * @return
     */
    public void hset(String key,String field,String val){
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        HashOperations ops = redisTemplate.opsForHash();
        ops.put(key,field,val);
    }

    /**
     * 删除值
     * @param key
     * 设置hash值
     * @param key
     * @param val
     * @return
     */
    public void hlist(String key,Map<String,String> val){
        HashOperations ops = stringRedisTemplate.opsForHash();
        ops.putAll(key,val);
    }

    /**
     * 取hash值
     * @param key
     * @return
     */
    public Map<String,String> glist2(Object key){
        HashOperations ops = stringRedisTemplate.opsForHash();
        return ops.entries(key);
    }


    /**
     * 删除hash值
     * @param key
     * @return
     */
    public void hdel(String key){
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        HashOperations ops = redisTemplate.opsForHash();

        redisTemplate.delete(key);
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public String get(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        return (String) ops.get(key);
    }

    /**

     * 获取值 返回object
     * @param key
     * @return
     */
    public Object getreturnobj(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * token
     * @param key
     * @return
     */
    public Map<String,String> glist(Object key){
        HashOperations hashOperations = stringRedisTemplate.opsForHash();
        return hashOperations.entries(key);
    }


    /**
     * 设置值
     * @param key
     * @param val
     */
    public void set(String key,Object val){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key, val);
    }

    /**
     * 设置值
     * @param key
     * @param val
     * @param time
     */
    public void set(String key,Object val,Integer time){
        set(key, val,TimeUnit.SECONDS,time);
    }

    /**
     * 设置值
     * @param key
     * @param val
     * @param time
     */
    public void set(String key,Object val,TimeUnit timeUnit,Integer time){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key, val,time, timeUnit);
    }


    /**
     * 删除
     * @param key
     * @return
     */
    public boolean delete(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate.delete(key);
    }

    /**
     * 获取过期的时间
     * @param key
     * @return
     */
    public long getTTL(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 设置过期的时间
     * @param key
     * @return
     */
    public boolean setTTL(String key,int second){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate.expire(key,second,TimeUnit.SECONDS);
    }
}
