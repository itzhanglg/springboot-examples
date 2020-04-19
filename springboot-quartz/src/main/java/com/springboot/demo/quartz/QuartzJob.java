package com.springboot.demo.quartz;


import com.springboot.demo.service.UserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zlg
 * @create 2020-03-17 13:51
 * 定时任务类: Job类 ,做什么事情
 */
public class QuartzJob implements Job{

    @Autowired
    private UserService userService;

    /**
     * 任务被触发时所执行的方法
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("date: "+
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        .format(LocalDateTime.now()));

        userService.addUser();
    }
}
