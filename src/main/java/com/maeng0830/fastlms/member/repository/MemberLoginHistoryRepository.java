package com.maeng0830.fastlms.member.repository;

import com.maeng0830.fastlms.member.entity.MemberLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberLoginHistoryRepository extends JpaRepository<MemberLoginHistory, Long> {
    Optional<List<MemberLoginHistory>> findByUserIdOrderByLoginDtDesc(String userId);
}
