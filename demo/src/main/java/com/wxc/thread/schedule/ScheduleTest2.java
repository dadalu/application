package com.wxc.thread.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
//@Component
public class ScheduleTest2 {
    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() {
        System.out.println(Thread.currentThread()+",task2,"+new Date());
    }
}
