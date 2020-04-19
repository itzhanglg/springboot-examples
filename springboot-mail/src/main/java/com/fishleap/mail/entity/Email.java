package com.fishleap.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author zlg
 * @create 2020-03-13 14:46
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Email implements Serializable {

    private static final long serialVersionUID = 11L;

    //必填
    private String[] email;   //接收方邮件
    private String subject; //主题
    private String content; //邮件内容

    //选填
    private String template;    //模板
    private HashMap<String, String> map;    //自定义参数,可以给freemarker/thymeleaf页面

}
