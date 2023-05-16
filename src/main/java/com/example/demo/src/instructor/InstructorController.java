package com.example.demo.src.instructor;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.email.model.PostMailReq;
import com.example.demo.src.instructor.InstructorProvider;
import com.example.demo.src.instructor.InstructorService;
import com.example.demo.src.instructor.model.*;
import com.example.demo.utils.JwtService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final InstructorProvider instructorProvider;
    @Autowired
    private final InstructorService instructorService;
    @Autowired
    private final JwtService jwtService;


    public InstructorController(InstructorProvider instructorProvider, InstructorService instructorService, JwtService jwtService){
        this.instructorProvider = instructorProvider;
        this.instructorService = instructorService;
        this.jwtService = jwtService;
    }


    /**
     * 전체 강사 목록 조회 API
     * [GET] /instructors
     * @return List<GetInstructorsRes>
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetInstructorsRes>> getInstructors() {
        try {
            List<GetInstructorsRes> getInstructorsRes= instructorProvider.getInstructors();
            return new BaseResponse<>(getInstructorsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 지역별 강사 목록 출력 API
     * @param region
     * @return List<getRegionInstructorsRes>
     */
    @ResponseBody
    @GetMapping("/{region}")
    public BaseResponse<List<GetRegionInstrsRes>> getRegionInstrs (@PathVariable("region") String region) {
        try {
            List<GetRegionInstrsRes> getRegionInstructorsRes= instructorProvider.getRegionInstructors(region);
            return new BaseResponse<>(getRegionInstructorsRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
