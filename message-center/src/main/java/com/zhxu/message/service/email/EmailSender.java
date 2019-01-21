package com.zhxu.message.service.email;

import com.zhxu.message.model.email.EmailParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailSender {

    @Autowired

    /**
     * 邮件参数配置
     * @return
     */
    public Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.smtp.starttls.required", "true");
        properties.setProperty("mail.debug", "false");// 是否显示调试信息(可选)
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put(" mail.smtp.timeout ", " 25000 ");
        return properties;
    }

    public void send(EmailParam param) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(properties());
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(param.getHost());
        mailSender.setUsername(param.getUserName());
        mailSender.setPassword(param.getPassword());
        mailSender.setPort(param.getPort());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(param.getUserName());
        mailMessage.setTo(param.getEmails().toArray(new String[param.getEmails().size()]));
        mailMessage.setSubject(param.getTitle());
        mailMessage.setText(param.getContent());

        mailSender.send(mailMessage);

    }
}
