package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on 2019/5/7.
 *
 * @author yangsen
 */
@org.springframework.stereotype.Service
@Component
public class IServiceImpl implements Service {

    @Autowired
    RedisTemplate redisTemplate;

    static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(100);

    public static ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue();

    private static AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public Integer shop(String name) {
        /*double result = redisTemplate.opsForValue().increment("1", 1);
        if (result > 200) {
            return -1;
        } else {
            System.out.println("抢购"+atomicLong.incrementAndGet());
            try {
                arrayBlockingQueue.put(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        concurrentLinkedQueue.offer(name);

        return 1;
    }

    @Autowired
    Rlock rlock;

    @Override
    public Integer shop1(String name) {
        /*if (rlock.lock("1")) {
            String result = redisTemplate.opsForValue().get("1").toString();
            if (Integer.valueOf(result) <= 0) {
                return -1;
            } else {
                try {
                    redisTemplate.opsForValue().set("1",Integer.valueOf(result)-1);
                    arrayBlockingQueue.put(name);
                    rlock.unlock("1");
                    return 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                while (true) {
                    Thread.sleep(2000);
                    if (rlock.lock("1")) {
                        String result = redisTemplate.opsForValue().get("1").toString();
                        if (Integer.valueOf(result) <= 0) {
                            return -1;
                        }
                        else {
                            try {
                                redisTemplate.opsForValue().set("1", Integer.valueOf(result)-1);
                                arrayBlockingQueue.put(name);
                                rlock.unlock("1");
                                return 1;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        return 1;
    }

}
