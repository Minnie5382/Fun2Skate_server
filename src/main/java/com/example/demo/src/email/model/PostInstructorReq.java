package com.example.demo.src.email.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostInstructorReq {
    // 이름, 이메일주소, 경력, 소개, 프로필 사진 경로, List<지역>
    private String name;
    private String email;
    private int experience;
    private String introducing;
    private MultipartFile profileImg;
    private String kakaoId;
    private String regions;


}
