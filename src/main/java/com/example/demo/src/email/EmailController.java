package com.example.demo.src.email;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.email.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
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
    @PostMapping("/email")
    public BaseResponse<String> sendEmail (@ModelAttribute PostMailReq postMailReq){
        try {
            emailService.sendMail(postMailReq, Integer.parseInt(postMailReq.getInstrIdx()));
            return new BaseResponse<>("메일 전송이 완료되었습니다.");
        } catch (Exception exception) {
            return new BaseResponse<>(exception.getMessage());
        }
    }

    /**
     * 강사 지원 메일 전송 API
     * [POST] email
     * @param postInstructorReq
     * @return String
     */

    @ResponseBody
    @PostMapping("/instructors/apply")
    public BaseResponse<String> postInstructor (@ModelAttribute PostInstructorReq postInstructorReq){
        MultipartFile profileImage = postInstructorReq.getProfileImg();
        if (profileImage.isEmpty()) {
            logger.warn("profile image is null");
        } else if (!Objects.requireNonNull(profileImage.getContentType()).startsWith("image")) {
            logger.warn("this file is not image type");
            return new BaseResponse<>(HttpStatus.FORBIDDEN.getReasonPhrase());
        }
        try {
            emailService.sendApplyEmail(postInstructorReq, profileImage);
            return new BaseResponse<>("메일 전송이 완료되었습니다.");
        } catch (Exception exception) {
            return new BaseResponse<>(exception.getMessage());
        }
    }

}
