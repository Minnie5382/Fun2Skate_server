package com.example.demo.src.email.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostMailReq {
    // 이름, 지역, 날짜, 메시지, 카카오톡 ID, 강사 Idx
    private String name;
    private String region;
    private String date;
    private String message;
    private String kakaoId;
    private String instrIdx;

}
