package com.springboot.demo.config;

import com.springboot.demo.quartz.QuartzJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @author zlg
 * @create 2020-03-17 21:33
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private MyJobFactory myJobFactory;

    /**
     * 1.创建 Job 对象
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        //关联我们自己的 Job 类
        jobDetail.setJobClass(QuartzJob.class);
        return jobDetail;
    }

    /**
     * 2.创建 Trigger 对象
     * 简单的 Trigger
     */
    /*@Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        SimpleTriggerFactoryBean simpleTrigger = new SimpleTriggerFactoryBean();
        //关联 JobDetail 对象
        simpleTrigger.setJobDetail(jobDetailFactoryBean.getObject());
        //该参数表示一个执行的毫秒数
        simpleTrigger.setRepeatInterval(2000);
        //重复次数
        simpleTrigger.setRepeatCount(5);
        return simpleTrigger;
    }*/

    /**
     * Cron表达式
     */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
        //关联 JobDetail 对象
        cronTrigger.setJobDetail(jobDetailFactoryBean.getObject());
        //设置cron表达式,触发时间
        cronTrigger.setCronExpression("0/2 * * * * ?");
        return cronTrigger;
    }

    /**
     * 3.创建 Scheduler 对象
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean){
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        //关联 trigger
        scheduler.setTriggers(cronTriggerFactoryBean.getObject());
        //设置自定义的jobfactory,将jobInstance注入IOC容器,解决Job类注入其它bean时的异常
        scheduler.setJobFactory(myJobFactory);
        return scheduler;
    }

}
