package com.maeng0830.fastlms.member.service;

import com.maeng0830.fastlms.admin.dto.MemberDto;
import com.maeng0830.fastlms.admin.dto.MemberLoginHistoryDto;
import com.maeng0830.fastlms.admin.model.MemberParam;
import com.maeng0830.fastlms.course.model.ServiceResult;
import com.maeng0830.fastlms.member.model.MemberInput;
import com.maeng0830.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    boolean emailAuth(String uuid);

    boolean sendResetPassword(ResetPasswordInput parameter);

    boolean resetPassword(String uuid, String password);

    boolean checkResetPassword(String uuid);

    List<MemberDto> list(MemberParam parameter);

    MemberDto detail(String userId);

    boolean updateStatus(String userId, String userStatus);

    boolean updatePassword(String userId, String password);

    // 회원 정보 수정
    ServiceResult updateMember(MemberInput parameter);

    // 회원 정보 페이지에서 비밀번호 변경
    ServiceResult updateMemberPassword(MemberInput parameter);

    // 회원 탈퇴
    ServiceResult withdraw(String userId, String password);


    List<MemberLoginHistoryDto> memberLoginHistory(String userId);
}
