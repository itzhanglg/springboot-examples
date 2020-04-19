package com.transsion.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zlg
 * @create 2020-03-11 10:41
 */
@Service
public class AsyncService {

    @Async  //标注方法是异步方法,会从线程池另外开启一个线程
    public void asyncTest(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("加载中...");
    }

}
