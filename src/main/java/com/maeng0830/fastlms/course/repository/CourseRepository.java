package com.maeng0830.fastlms.course.repository;

import com.maeng0830.fastlms.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
