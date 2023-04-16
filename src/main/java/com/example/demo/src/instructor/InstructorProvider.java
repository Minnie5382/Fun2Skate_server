package com.example.demo.src.instructor;


import com.example.demo.config.BaseException;
import com.example.demo.src.instructor.InstructorDao;
import com.example.demo.src.instructor.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class InstructorProvider {

    private final InstructorDao instructorDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public InstructorProvider(InstructorDao instructorDao, JwtService jwtService) {
        this.instructorDao = instructorDao;
        this.jwtService = jwtService;
    }

    // 전체 강사 목록 조회
    public List<GetInstructorsRes> getInstructors () throws BaseException {
        try {
            List<GetInstructorsRes> getInstructorsRes = instructorDao.retrieveInstructors();
            return getInstructorsRes;

        } catch (Exception exception) {
            logger.error("App - getInstructors Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 지역별 강사목록 조회
    public List<GetRegionInstrsRes> getRegionInstructors (String region) throws BaseException {
        // 존재하는 지역인가?
        if(instructorDao.checkRegionExists(region) == 0)
            throw new BaseException(GET_INVALID_REGION);

        try {
            List<GetRegionInstrsRes> getRegionInstrsRes = instructorDao.retrieveRegionInstructors(region);
            return getRegionInstrsRes;

        } catch (Exception exception) {
            logger.error("App - getRegionInstructors Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
