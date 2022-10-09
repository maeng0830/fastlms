package com.maeng0830.fastlms.admin.repository;

import com.maeng0830.fastlms.admin.entity.Category;
import com.maeng0830.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
