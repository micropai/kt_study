package com.example.springbasic.controller;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String memberName;
    private String departmentCd;
    private String positionCd;
    private String cellPhone;
}
