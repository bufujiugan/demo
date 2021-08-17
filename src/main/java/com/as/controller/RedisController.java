package com.as.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RedisController {

    //锁名称
    public static final String LOCK_PREFIX = "redis_lock";
    //加锁失效时间，毫秒
    public static final int LOCK_EXPIRE = 500000; // ms

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis")
    public void testRedis(@RequestParam("key") String key) {
        new Thread(() -> {
            try {
                if(lock(key)) {
//                    System.out.println(getNow());
                    System.err.println("lock1=====" + redisTemplate.hasKey((LOCK_EXPIRE + key)));
                    System.out.println("Get lock1 success!!!");
                    Thread.sleep(1000);
                } else {
//                    System.out.println(getNow());
                    System.out.println("Get lock1 wait...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                delete(key);
                System.out.println("del lock1");
            }
        }).start();
        new Thread(() -> {
            try {
                if(lock(key)) {
//                    System.out.println(getNow());
                    System.err.println("lock2=====" + redisTemplate.hasKey((LOCK_EXPIRE + key)));
                    System.out.println("Get lock2 success!!!");
                    Thread.sleep(2000);
                } else {
//                    System.out.println(getNow());
                    System.out.println("Get lock2 wait...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("del lock2");
                delete(key);
            }
        }).start();
    }

    /**
     *  最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key){
        String lock = LOCK_PREFIX + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    // 如果锁已经过期
                    if (expireTime < System.currentTimeMillis()) {
                        // 重新加锁，防止死锁
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    /**
     * 删除锁
     *
     * @param key
     */
    @RequestMapping("/del")
    public void delete(@RequestParam("key") String key) {
        redisTemplate.delete(LOCK_EXPIRE + key);
    }


    public String getNow() {
        return DateUtil.now();
    }
}
