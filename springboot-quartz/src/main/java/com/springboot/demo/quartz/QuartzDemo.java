package com.springboot.demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author zlg
 * @create 2020-03-17 14:06
 * 使用主函数测试QuartzJob类  -- 测试任务类
 */
public class QuartzDemo {

    public static void main(String[] args) {
        // 1.创建 Job 对象：你要做什么事？
        JobDetail job = JobBuilder.newJob(QuartzJob.class).build();
        /*
            简单的 trigger 触发时间：通过 Quartz 提供一个方法来完成简单的重复调用
            2.创建 Trigger 对象：在什么时间做？
         */
        /*Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
                .build();*/

        //Trigger：按照 Cron 的表达式来给定触发的时间
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();
        // 3.创建 Scheduler 对象：在什么时间做什么事？
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(job, cronTrigger);
            //启动
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
