package com.fishleap.mail.controller;

import com.fishleap.mail.entity.Email;
import com.fishleap.mail.entity.Result;
import com.fishleap.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlg
 * @create 2020-03-13 16:17
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    private static final Logger log = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private MailService mailService;

    //发送freemarker页面
    @PostMapping("/sendf")
    public Result sendFreemarker(@RequestBody Email mail) {
        log.debug("REST request to send mail of {}", mail.getSubject());
        try {
            mailService.sendFreemarker(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }

    //发送html页面和添加附件
    @PostMapping("/sendh")
    public Result sendHtml(@RequestBody Email mail) {
        log.debug("REST request to send mail of {}", mail.getSubject());
        try {
            mailService.sendHtml(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }

    //发送简单邮件消息
    @PostMapping("/send")
    public Result send(@RequestBody Email mail) {
        log.debug("REST request to send mail of {}", mail.getSubject());
        try {
            mailService.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }

    //发送thymeleaf页面
    @PostMapping("/sendt")
    public Result sendThymeleaf(@RequestBody Email mail) {
        log.debug("REST request to send mail of {}", mail.getSubject());
        try {
            mailService.sendThymeleaf(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }


}
