package com.example.springbasic.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberName;
    private String departmentCd;
    private String positionCd;
    private String cellPhone;


}