package com.maeng0830.fastlms.member.service;

import com.maeng0830.fastlms.admin.dto.MemberDto;
import com.maeng0830.fastlms.admin.model.MemberParam;
import com.maeng0830.fastlms.member.entity.Member;
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
}
