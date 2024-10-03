package io.hhplus.tdd.infrastructure.repository;

import io.hhplus.tdd.domain.lecture.LectureEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaLectureRepository extends JpaRepository<LectureEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT l FROM LectureEntity l WHERE l.lectureNo = :lectureNo")
    Optional<LectureEntity> findByIdWithLock(@Param("lectureNo") long lectureNo);

}