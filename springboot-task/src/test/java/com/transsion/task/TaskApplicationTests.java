package com.transsion.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class TaskApplicationTests {

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Test
	void mailSenderTest() {
		//创建简单消息邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("数据展示网页讨论");
		message.setText("请在今晚7点参与会议讨论");

		message.setTo("18986695894@163.com");
		message.setFrom("1321340821@qq.com");

		mailSender.send(message);
	}

	@Test
	void mailSenderTest2() throws MessagingException {
		//创建复杂消息邮件
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setSubject("测试平台讨论");
		messageHelper.setText("<h3 style='color:red;'>今晚6点请参与会议!</h3>",true);

		//添加附件
		messageHelper.addAttachment("0.jpg",new File("C:\\Users\\admin\\Pictures\\Feedback\\0.jpg"));
		messageHelper.addAttachment("任务表.xls",new File("C:\\Users\\admin\\Desktop\\任务表.xls"));

		messageHelper.setTo("18986695894@163.com");
		message.setFrom("1321340821@qq.com");

		mailSender.send(message);
	}

}
