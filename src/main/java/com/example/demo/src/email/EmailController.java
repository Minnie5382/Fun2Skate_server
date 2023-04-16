package com.example.demo.src.email;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.email.model.PostMailReq;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/email")
public class EmailController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final EmailService emailService;
    @Autowired
    private final JwtService jwtService;


    public EmailController(EmailService emailService, JwtService jwtService){
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    /**
     * 강사 메일 전송 API
     * [POST] /email
     * @param postMailReq
     * @return String
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<String> sendEmail (@ModelAttribute PostMailReq postMailReq){
        try {
            emailService.sendMail(postMailReq, Integer.parseInt(postMailReq.getInstrIdx()));
            return new BaseResponse<>("메일 전송이 완료되었습니다");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
