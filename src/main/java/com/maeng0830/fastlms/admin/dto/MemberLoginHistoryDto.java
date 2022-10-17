package com.maeng0830.fastlms.admin.dto;

import com.maeng0830.fastlms.member.entity.MemberLoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberLoginHistoryDto {
    String userId;
    LocalDateTime loginDt;
    String loginIp;
    String loginAgent;

    //추가 컬럼
    long totalCount;
    long seq;

    public static MemberLoginHistoryDto of(MemberLoginHistory memberLoginHistory, int curSeq, int loginTotalCount) {
        return MemberLoginHistoryDto.builder()
                .userId(memberLoginHistory.getUserId())
                .loginDt(memberLoginHistory.getLoginDt())
                .loginIp(memberLoginHistory.getLoginIp())
                .loginAgent(memberLoginHistory.getLoginAgent())
                .seq(curSeq)
                .totalCount(loginTotalCount)
                .build();
    }

    public static List<MemberLoginHistoryDto> of(List<MemberLoginHistory> memberLoginHistories) {

        if (memberLoginHistories == null) {
            return null;
        }

        int curSeq = 1;

        List<MemberLoginHistoryDto> memberLoginHistoryDtoList = new ArrayList<>();

        for (MemberLoginHistory x: memberLoginHistories) {
            memberLoginHistoryDtoList.add(MemberLoginHistoryDto.of(x, curSeq, memberLoginHistories.size()));
            curSeq++;
        }

        return memberLoginHistoryDtoList;
    }

    public String getLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return loginDt != null ? loginDt.format(formatter) : "";
    }
}
