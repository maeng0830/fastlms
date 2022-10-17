package com.maeng0830.fastlms.configuration;

import com.maeng0830.fastlms.member.entity.Member;
import com.maeng0830.fastlms.member.entity.MemberLoginHistory;
import com.maeng0830.fastlms.member.repository.MemberLoginHistoryRepository;
import com.maeng0830.fastlms.member.repository.MemberRepository;
import com.maeng0830.fastlms.util.LoginHistoryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberLoginHistoryRepository memberLoginHistoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String userId = authentication.getName();
        String loginIp = LoginHistoryUtil.loginHistoryUtil(request)[0];
        String loginAgent = LoginHistoryUtil.loginHistoryUtil(request)[1];
        LocalDateTime loginDt = LocalDateTime.now();

        MemberLoginHistory memberLoginHistory = MemberLoginHistory.builder()
                .userId(userId)
                .loginDt(loginDt)
                .loginIp(loginIp)
                .loginAgent(loginAgent)
                .build();

        memberLoginHistoryRepository.save(memberLoginHistory);

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setLoginDt(loginDt);
            memberRepository.save(member);
        }


        super.onAuthenticationSuccess(request, response, authentication);
    }
}
