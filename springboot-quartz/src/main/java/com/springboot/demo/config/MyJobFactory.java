package com.springboot.demo.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author zlg
 * @create 2020-03-17 22:19
 * 解决Job类注入bean时产生的异常:
 * org.quartz.SchedulerException: Job threw an unhandled exception.
 */
@Component
public class MyJobFactory extends AdaptableJobFactory {

    //AutowireCapableBeanFactory 可以将一个对象添加到 SpringIOC 容器中，并且完成该对象注入
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    /**
     * 该方法需要将实例化的任务对象手动的添加到 springIOC 容器中并且完成对象的注入
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        //将 obj 对象添加 Spring IOC 容器中，并完成注入
        autowireCapableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
