package com.transsion.task.controller;

import com.transsion.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlg
 * @create 2020-03-11 10:49
 */
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/asynctask")
    public String asyncTask(){
        asyncService.asyncTest();
        return "data processing";
    }
}
