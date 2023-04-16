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
public class GetInstructorsRes {
    // 강사 idx, 이름, 경력, 소개, 프로필 사진 경로, List<지역>
    private int instrIdx;
    private String name;
    private int experience;
    private String introducing;
    private String profileImgPath;
    private List<GetRegion> regions;

}
