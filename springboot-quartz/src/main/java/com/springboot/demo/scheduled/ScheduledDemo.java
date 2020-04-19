package com.springboot.demo.scheduled;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zlg
 * @create 2020-03-17 13:32
 * 第一种定时任务: 使用@scheduled注解做相对于简单的定时任务
 */
@Component
public class ScheduledDemo {

    /**
     * 定时任务方法
     * @Scheduled:设置定时任务
     * cron 属性：cron 表达式。定时任务触发是时间的一个字符串表达形式
     */
//    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduledMethod(){
        System.out.println("定时方法被触发"+
                DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")
                        .format(LocalDateTime.now()));
    }

}
