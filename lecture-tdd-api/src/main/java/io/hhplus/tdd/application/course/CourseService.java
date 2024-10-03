package io.hhplus.tdd.application.course;

import io.hhplus.tdd.domain.course.CourseEntity;
import io.hhplus.tdd.infrastructure.repository.JpaCourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CourseService {

    private final JpaCourseRepository jpaCourseRepository;

    public CourseService(JpaCourseRepository jpaCourseRepository) {
        this.jpaCourseRepository = jpaCourseRepository;
    }

    public void enrollUser(long userId, long lectureNo) {
        CourseEntity course = CourseEntity.builder()
                .userId(userId)
                .lectureNo(lectureNo)
                .createDate(LocalDateTime.now())
                .build();

        jpaCourseRepository.save(course);
    }

}