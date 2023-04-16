package com.example.demo.src.email;

import com.example.demo.src.email.model.PostMailReq;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;



@Service
@RequiredArgsConstructor
public class EmailService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender mailSender;
    private final EmailDao emailDao;
    private static final String ADMIN_ADDRESS = "minik001@naver.com";

    @Autowired
    public EmailService(EmailDao emailDao, JavaMailSender mailSender) {
        this.emailDao = emailDao;
        this.mailSender = mailSender;
    }

    @Async
    public void sendMail (PostMailReq postMailReq, int instrIdx) throws UnsupportedEncodingException, MessagingException {
        String TO_ADDRESS = emailDao.retrieveEmailAddress(instrIdx);
        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, TO_ADDRESS);
        message.setSubject("Sent a Skate Lesson Request from Fun2Skate!");
        String text = "";
        text += "Sent a Skate Lesson Request from Fun2Skate!\nFun2Skate 사이트에서 스케이트보드 레슨 요청서가 도착했습니다!\n\n";
        text += "Name : " + postMailReq.getName() + " \n ";
        text += "Region : " + postMailReq.getRegion() + " \n ";
        text += "Date : " + postMailReq.getDate() + "\n";
        text += "KakaoTalk ID : " + postMailReq.getKakaoId()+ "\n";
        text += "Message : " + postMailReq.getMessage() + "\n";
        message.setText(text, "utf-8");
        message.setFrom(new InternetAddress(ADMIN_ADDRESS, "fun2skate_admin"));
        mailSender.send(message);
    }
}