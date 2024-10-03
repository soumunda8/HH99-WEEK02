package io.hhplus.tdd.application.course;

import io.hhplus.tdd.infrastructure.repository.JpaCourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final JpaCourseRepository jpaCourseRepository;

    public CourseService(JpaCourseRepository jpaCourseRepository) {
        this.jpaCourseRepository = jpaCourseRepository;
    }

}