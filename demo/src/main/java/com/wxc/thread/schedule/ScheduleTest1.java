package com.wxc.thread.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

//@Component
public class ScheduleTest1 {
    @Async
    @Scheduled(cron = "0/5 * * * * ?")
        public void task1() {
            System.out.println(Thread.currentThread()+",task1,"+new Date());
        }

}
