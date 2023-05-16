package com.example.demo.src.instructor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String profileImgPath;
    private String kakaoId;
    private String regions;


}
