package com.maeng0830.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity // 테이블
public class Member implements MemberCode {
    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    private LocalDateTime loginDt;

    private boolean adminYn;

    private String userStatus; // 이용가능, 정지

    private String zipcode;
    private String addr;
    private String addrDetail;
}
