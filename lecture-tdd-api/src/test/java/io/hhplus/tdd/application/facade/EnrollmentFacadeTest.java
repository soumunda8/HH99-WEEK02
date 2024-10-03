package io.hhplus.tdd.application.facade;

import io.hhplus.tdd.domain.lecture.LectureEntity;
import io.hhplus.tdd.infrastructure.repository.JpaCourseRepository;
import io.hhplus.tdd.infrastructure.repository.JpaLectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EnrollmentFacadeTest {

    @Autowired
    private EnrollmentFacade enrollmentFacade;

    @Autowired
    private JpaCourseRepository jpaCourseRepository;

    @Autowired
    private JpaLectureRepository jpaLectureRepository;

    @BeforeEach
    public void setUp() {
        // 강의 초기 설정
        LectureEntity lecture = LectureEntity.builder()
                .lectureName("테스트 강의")
                .applyCnt(0)
                .build();
        jpaLectureRepository.save(lecture);
    }

    // 통합 테스트 01 : 동시 수강 신청 (40명 신청 중 30명 제한)
    @Test
    @Transactional
    public void testEnrollOver() throws InterruptedException {
        // given
        long lectureNo = 5L;
        int totalUsers = 40;
        ExecutorService executorService = Executors.newFixedThreadPool(totalUsers);
        List<Long> successfulEnrollments = new ArrayList<>();

        // when
        for (long userId = 1L; userId <= totalUsers; userId++) {
            long finalUserId = userId;
            executorService.submit(() -> {
                try {
                    enrollmentFacade.enrollUser(finalUserId, lectureNo);
                    synchronized (successfulEnrollments) {
                        successfulEnrollments.add(finalUserId);
                    }
                } catch (IllegalArgumentException e) {}
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }

        // then
        assertEquals(30, successfulEnrollments.size());

        LectureEntity updatedLectureEntity = jpaLectureRepository.findById(lectureNo).orElseThrow();
        assertEquals(30, updatedLectureEntity.getApplyCnt());
    }

    // 통합 테스트 02 : 중복 수강 신청
    @Test
    public void testApplyMulti() {
        // given
        long userId = 1L;
        long lectureNo = 1L;

        // when
        enrollmentFacade.enrollUser(userId, lectureNo);

        for (int i = 0; i < 4; i++) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                enrollmentFacade.enrollUser(userId, lectureNo);
            });
            assertEquals("이미 신청된 강의", exception.getMessage());
        }

        boolean isEnrolled = jpaCourseRepository.existsByUserIdAndLectureNo(userId, lectureNo);
        assertTrue(isEnrolled);
    }

}
