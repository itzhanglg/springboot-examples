package com.fishleap.mail.service.impl;

import com.fishleap.mail.common.Constants;
import com.fishleap.mail.entity.Email;
import com.fishleap.mail.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zlg
 * @create 2020-03-13 14:59
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;  //执行者
    @Autowired
    private Configuration configuration;    //freemarker
    @Autowired
    private SpringTemplateEngine templateEngine;    //thymeleaf
    //读取配置文件中mail.username(1321340821@qq.com)值并赋值给USER_NAME
    @Value("${spring.mail.username}")
    public String USER_NAME;    //发送者
    @Value("http://localhost:8088")
    public String PATH; //发送者路径,像图片,文件的路径访问

    //发送简单消息
    @Override
    public void send(Email mail) throws Exception {
        logger.info("发送简单邮件消息: {}",mail.getContent());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USER_NAME);
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailSender.send(message);
    }

    //发送html页面消息
    @Override
    public void sendHtml(Email mail) throws Exception {
        logger.info("发送html页面和添加附件邮件消息: {}", mail.getContent());
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //也可以自定义发信名称,如:平台网站
        helper.setFrom(USER_NAME, "平台网站");
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        helper.setText(
            "<html><body><h3 style='color:red;'>邮件发送</h3>" +
                "<img src=\"cid:springcloud\" ></body></html>",true
        );
        //发送图片
        File file = ResourceUtils.getFile("classpath:static"
            + Constants.SF_FILE_SEPARATOR + "image"
            + Constants.SF_FILE_SEPARATOR + "springcloud.png");
        helper.addInline("springcloud", file);
        //发送附件
        file = ResourceUtils.getFile("classpath:static"
                + Constants.SF_FILE_SEPARATOR + "file"
                + Constants.SF_FILE_SEPARATOR + "Mail.zip");
        helper.addAttachment("mail源码",file);
        mailSender.send(mimeMessage);
    }

    //发送freemarker页面消息
    @Override
    public void sendFreemarker(Email mail) throws Exception {
        logger.info("发送freemarker页面邮件消息: {}", mail.getContent());
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(USER_NAME);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());

        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        model.put("path", PATH);
        Template template = configuration.getTemplate(mail.getTemplate());
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(content, true);
        mailSender.send(mimeMessage);
    }

    //发送thymeleaf页面消息
    @Override
    public void sendThymeleaf(Email mail) throws Exception {
        logger.info("发送thymeleaf页面邮件消息: {}", mail.getContent());
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(USER_NAME);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Context context = new Context();
        context.setVariable("email", mail);
        String text = templateEngine.process(mail.getTemplate(), context);
        helper.setText(text, true);
        mailSender.send(mimeMessage);
    }

}
