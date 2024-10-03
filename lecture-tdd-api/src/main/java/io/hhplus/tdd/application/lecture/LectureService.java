package io.hhplus.tdd.application.lecture;

import io.hhplus.tdd.infrastructure.repository.JpaLectureRepository;
import org.springframework.stereotype.Service;

@Service
public class LectureService {

    private final JpaLectureRepository jpaLectureRepository;

    public LectureService(JpaLectureRepository jpaLectureRepository) {
        this.jpaLectureRepository = jpaLectureRepository;
    }

}