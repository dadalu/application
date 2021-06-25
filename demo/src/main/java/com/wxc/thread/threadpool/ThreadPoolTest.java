package com.wxc.thread.threadpool;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.*;

@Component
public class ThreadPoolTest {
    //@PostConstruct
    public void exe() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,4,2, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        executor.allowsCoreThreadTimeOut();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread()+" started...,"+new Date());
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+" end...,"+new Date());
            });
        }
    }
}
