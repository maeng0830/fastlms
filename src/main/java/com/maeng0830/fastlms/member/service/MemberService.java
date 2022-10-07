package com.maeng0830.fastlms.member.service;

import com.maeng0830.fastlms.member.model.MemberInput;
import com.maeng0830.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    boolean emailAuth(String uuid);

    boolean sendResetPassword(ResetPasswordInput parameter);

    boolean resetPassword(String uuid, String password);

    boolean checkResetPassword(String uuid);
}
