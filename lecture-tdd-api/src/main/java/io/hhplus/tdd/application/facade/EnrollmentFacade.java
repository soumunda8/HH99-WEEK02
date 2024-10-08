package io.hhplus.tdd.application.facade;

import io.hhplus.tdd.application.course.CourseService;
import io.hhplus.tdd.application.lecture.LectureService;
import io.hhplus.tdd.domain.lecture.Lecture;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentFacade {

    private final LectureService lectureService;
    private final CourseService courseService;

    public EnrollmentFacade(LectureService lectureService, CourseService courseService) {
        this.lectureService = lectureService;
        this.courseService = courseService;
    }

    // 전체 강의 목록
    public List<Lecture> getLectureList() {
        return lectureService.getLectureList();
    }

    // 강의 신청 유무 확인
    public void validateEnrollment(long userId, long lectureNo) {
        courseService.validateEnrollment(userId, lectureNo);
    }

    // 강의 등록
    @Transactional
    public void enrollUser(long userId, long lectureNo) {
        lectureService.updateApplyCnt(lectureNo);
        courseService.enrollUser(userId, lectureNo);
    }

}