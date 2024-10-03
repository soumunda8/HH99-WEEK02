package io.hhplus.tdd.infrastructure.repository;

import io.hhplus.tdd.domain.lecture.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLectureRepository extends JpaRepository<LectureEntity, Long> {
}
