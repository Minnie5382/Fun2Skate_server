package com.example.demo.src.instructor;


import com.example.demo.config.BaseException;
import com.example.demo.src.instructor.InstructorDao;
import com.example.demo.src.instructor.InstructorService;
import com.example.demo.src.instructor.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class InstructorService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final InstructorDao instructorDao;
    private final InstructorProvider instructorProvider;
    private final JwtService jwtService;


    @Autowired
    public InstructorService(InstructorDao instructorDao, InstructorProvider instructorProvider, JwtService jwtService) {
        this.instructorDao = instructorDao;
        this.instructorProvider = instructorProvider;
        this.jwtService = jwtService;
    }


}
