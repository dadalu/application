package com.wxc.thread.schedule;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
//@Component
public class ScheduleTest {
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+",task,"+new Date());
    }
}
