package com.fishleap.mail.service;

import com.fishleap.mail.entity.Email;

/**
 * @author zlg
 * @create 2020-03-13 14:56
 */
public interface MailService {
    //发送简单消息
    public void send(Email mail) throws Exception;
    //发送html页面消息
    public void sendHtml(Email mail) throws Exception;
    //发送freemarker页面消息
    public void sendFreemarker(Email mail) throws Exception;
    //发送thymeleaf页面消息
    public void sendThymeleaf(Email mail) throws Exception;

}
