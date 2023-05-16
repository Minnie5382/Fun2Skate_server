package com.example.demo.src.email;

import com.example.demo.src.email.model.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;



@Service
@RequiredArgsConstructor
public class EmailService {
    final String ADMIN_EMAIL_ADDRESS = "minik001@naver.com"; // 관리자 이메일 주소

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
        text += "[Skateboard Lesson Request Form]\n";
        text += "Name : " + postMailReq.getName() + " \n ";
        text += "Region : " + postMailReq.getRegion() + " \n ";
        text += "Date : " + postMailReq.getDate() + "\n";
        text += "KakaoTalk ID : " + postMailReq.getKakaoId()+ "\n";
        text += "Message : " + postMailReq.getMessage() + "\n";
        message.setText(text, "utf-8");
        message.setFrom(new InternetAddress(ADMIN_ADDRESS, "fun2skate_admin"));
        mailSender.send(message);
    }

    @Async
    public ResponseEntity<Object> sendApplyEmail (PostInstructorReq postInstructorReq, MultipartFile profileImage) throws IOException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(ADMIN_ADDRESS);
        helper.setFrom(new InternetAddress(ADMIN_ADDRESS, "fun2skate_admin"));

        // 본문 내용 설정
        String text = "";
        text += "Sent a Instructor Apply from Fun2Skate!\nFun2Skate 사이트에서 강사 지원서가 도착했습니다!\n\n";
        text += "Please check the attached file. \n첨부 파일을 확인하세요.\n\n";
        text += "[Skateboard Instructor Form]\n";
        text += "Name : " + postInstructorReq.getName() + " \n ";
        text += "Experience : " + postInstructorReq.getExperience() + " \n ";
        text += "introducing : " + postInstructorReq.getIntroducing() + " \n ";
        text += "Region : " + postInstructorReq.getRegions() + " \n ";
        text += "Kakao ID : " + postInstructorReq.getKakaoId() + " \n ";
        text += "Email Address : " + postInstructorReq.getEmail() + " \n ";


        helper.setSubject("Sent a Instructor Apply from Fun2Skate!");
        helper.setText(text);
        helper.addAttachment(profileImage.getOriginalFilename(), new ByteArrayResource(profileImage.getBytes()));
        mailSender.send(message);

        return ResponseEntity.ok().build();
    }
}