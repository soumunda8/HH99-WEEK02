package io.hhplus.tdd.infrastructure.repository;

import io.hhplus.tdd.domain.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCourseRepository extends JpaRepository<CourseEntity, Long> {
}
