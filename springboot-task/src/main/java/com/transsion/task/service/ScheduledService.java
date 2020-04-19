package com.transsion.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author zlg
 * @create 2020-03-11 10:59
 */
@Service
public class ScheduledService {

    /**
     * second(秒), minute(分), hour(时), day of month(日), month(月), day of week(周几)
     * 0 * * * * MON-FRI
     * 0 0/5 14,18 * * ?    每天14点整,和18点整,每隔5分钟执行一次
     * 0 15 10 ? * 1-6      每个月的周一至周六10:15分钟执行一次
     * 0 0 2 ? * 6L         每个月的最后一个周六凌晨2点执行一次
     * 0 0 2 LW * ?         每个月的最后一个工作日凌晨2点执行一次
     * 0 0 2-4 ? * 1#1      每个月的第一个周一凌晨2点到4点期间,每个整点都执行一次
     */
//    @Scheduled(cron = "0 * * * * *")
//    @Scheduled(cron = "0,1,2 * * * * MON-SAT")
//    @Scheduled(cron = "0-6 * * * * MON-SAT")
    @Scheduled(cron = "0 0 12 * * MON-SAT")
    public void scheduledTest(){
        System.out.println("scheduled ...");
    }

}
