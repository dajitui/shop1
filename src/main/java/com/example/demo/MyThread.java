package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on 2019/5/7.
 *
 * @author yangsen
 */
public class MyThread implements Runnable {

    @Resource
    Service service;

    private String i;

    CountDownLatch latch;

    @Override
    public void run() {
//        service.shop("dajitui" + i);
        IServiceImpl.concurrentLinkedQueue.offer(i);
    }

    public MyThread(String i, CountDownLatch latch,Service service) {
        this.service=service;
        this.latch = latch;
        this.i = i;
    }
}
