package com.wxc.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTest1 {
    @Scheduled(cron = "0/4 * * * * ?")
        public void task1() {
            System.out.println(Thread.currentThread()+",task1,"+new Date());
        }

}
