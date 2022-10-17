package com.maeng0830.fastlms.util;

import javax.servlet.http.HttpServletRequest;

public class LoginHistoryUtil {
    public static String[] loginHistoryUtil(HttpServletRequest request) {
        String[] arr = new String[2];

        String loginAgent = request.getHeader("user-agent");

        String loginIp = request.getHeader("X-Forwarded-For");

        if (loginIp == null || loginIp.length() == 0) {
            loginIp = request.getHeader("Proxy-Client-IP");
        }

        if (loginIp == null || loginIp.length() == 0) {
            loginIp = request.getHeader("WL-Proxy-Client-IP");
        }

        if (loginIp == null || loginIp.length() == 0) {
            loginIp = request.getRemoteAddr() ;
        }

        arr[0] = loginIp;
        arr[1] = loginAgent;

        return arr;
    }
}
