package io.hhplus.tdd.application.lecture;

import io.hhplus.tdd.domain.lecture.Lecture;
import io.hhplus.tdd.domain.lecture.LectureEntity;
import io.hhplus.tdd.infrastructure.repository.JpaLectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class LectureServiceTest {

    @Mock
    private JpaLectureRepository jpaLectureRepository;

    @InjectMocks
    private LectureService lectureService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 실패 01 : 강의 리스트 조회 시, 빈 리스트가 반환될 경우
    @Test
    public void testEmptyLectureList() {
        // given
        given(jpaLectureRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<Lecture> lectureList = lectureService.getLectureList();

        // then
        assertTrue(lectureList.isEmpty());
    }

    // 실패 02 : 강의 번호로 조회 시 강의가 없는 경우
    @Test
    public void testNoLectureNo() {
        long lectureNo = 1L;

        // given
        given(jpaLectureRepository.findById(lectureNo)).willReturn(Optional.empty());

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            lectureService.getLectureById(lectureNo);
        });

        // then
        assertEquals("등록된 강의가 없음", exception.getMessage());
    }

    // 성공 02 : 수강 신청시 applyCnt 증가
    @Test
    public void testUpdateApplyCnt_Success() {
        // given
        LectureEntity lecture = LectureEntity.builder()
                .lectureNo(5L)
                .lectureName("테스트 강의")
                .applyCnt(10)
                .build();

        when(jpaLectureRepository.findById(lecture.getLectureNo())).thenReturn(Optional.of(lecture));

        // when
        lectureService.updateApplyCnt(lecture.getLectureNo());

        // then
        ArgumentCaptor<LectureEntity> captor = ArgumentCaptor.forClass(LectureEntity.class);
        verify(jpaLectureRepository).save(captor.capture());

        assertEquals(11, captor.getValue().getApplyCnt());
    }

}
