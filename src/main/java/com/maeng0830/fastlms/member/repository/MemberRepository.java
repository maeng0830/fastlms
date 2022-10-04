package com.maeng0830.fastlms.member.repository;

import com.maeng0830.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
