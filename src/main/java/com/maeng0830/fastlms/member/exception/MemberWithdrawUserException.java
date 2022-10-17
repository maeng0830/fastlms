package com.maeng0830.fastlms.member.exception;

public class MemberWithdrawUserException extends RuntimeException {
    public MemberWithdrawUserException(String error) {
        super(error);
    }
}
