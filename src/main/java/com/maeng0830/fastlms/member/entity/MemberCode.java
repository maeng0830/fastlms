package com.maeng0830.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


public interface MemberCode {
    String MEMBER_STATUS_REQ = "REQ";

    // 현재 이용 중인 상태
    String MEMBER_STATUS_ING = "ING";

    String MEMBER_STATUS_STOP = "STOP";
}
