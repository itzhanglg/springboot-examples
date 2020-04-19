package com.xtkj.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author zlg
 * @create 2019-01-22 13:12
 */
@Controller
public class HelloController {

/*    @RequestMapping({"/","index.html"})
    public String index(){
        return "index";
    }*/

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


//    查出用户数据,在页面展示
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好!</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
//        classpath:/templates/success.html
        return "success";
    }

}
