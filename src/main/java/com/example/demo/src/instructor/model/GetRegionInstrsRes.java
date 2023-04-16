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
public class GetRegionInstrsRes {
    // 강사 idx, 이름, 프로필 사진
    private int instrIdx;
    private String name;
    private String profileImgPath;
}
