package io.hhplus.tdd.application.course;

import io.hhplus.tdd.domain.course.CourseEntity;
import io.hhplus.tdd.infrastructure.repository.JpaCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.*;

public class CourseServiceTest {

    @Mock
    private JpaCourseRepository jpaCourseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 성공 01 : 수강 신청
    @Test
    public void testEnroll() {
        // given
        long userId = 1L;
        long lectureNo = 103L;

        // when
        courseService.enrollUser(userId, lectureNo);

        // then
        verify(jpaCourseRepository, times(1)).save(any(CourseEntity.class));
    }

}