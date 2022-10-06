package com.maeng0830.fastlms.member.service;

import com.maeng0830.fastlms.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    boolean emailAuth(String uuid);
}
