package io.hhplus.tdd.interfaces.api.lecture;

import io.hhplus.tdd.application.facade.EnrollmentFacade;
import io.hhplus.tdd.domain.lecture.Lecture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lecture")
public class LectureController {

    private final EnrollmentFacade enrollmentFacade;

    public LectureController(EnrollmentFacade enrollmentFacade) {
        this.enrollmentFacade = enrollmentFacade;
    }

    /**
     * TODO - 전체 특강 목록
     */
    @GetMapping("/list")
    public List<Lecture> getAllList() {
        return enrollmentFacade.getLectureList();
    }
    
    /**
     * TODO - 특강 신청
     */
    @GetMapping("/{userId}/{lectureNo}")
    public String apply(@PathVariable long userId, @PathVariable long lectureNo) {

        if (userId <= 0) {
            throw new IllegalArgumentException("사용자 아이디 누락");
        }

        if (lectureNo <= 0) {
            throw new IllegalArgumentException("강의 번호 누락");
        }

        enrollmentFacade.enrollUser(userId, lectureNo);

        return "수강 신청 성공";
    }

}
