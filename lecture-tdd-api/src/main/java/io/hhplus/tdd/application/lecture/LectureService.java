package io.hhplus.tdd.application.lecture;

import io.hhplus.tdd.domain.lecture.Lecture;
import io.hhplus.tdd.domain.lecture.LectureEntity;
import io.hhplus.tdd.infrastructure.repository.JpaLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {

    private final ModelMapper modelMapper;

    private final JpaLectureRepository jpaLectureRepository;

    public LectureService(JpaLectureRepository jpaLectureRepository, ModelMapper modelMapper) {
        this.jpaLectureRepository = jpaLectureRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Lecture> getLectureList() {
        List<LectureEntity> lectureEntitieList = jpaLectureRepository.findAll();

        List<Lecture> lectureList = lectureEntitieList.stream()
                .map(lectureEntity -> modelMapper.map(lectureEntity, Lecture.class))
                .collect(Collectors.toList());

        return lectureList;
    }

    @Transactional
    public LectureEntity getLectureById(long lectureNo) {
        return jpaLectureRepository.findByIdWithLock(lectureNo)
                .orElseThrow(() -> new IllegalArgumentException("등록된 강의가 없음"));
    }

    @Transactional
    public void updateApplyCnt(long lectureNo) {
        LectureEntity lectureEntity = getLectureById(lectureNo);

        if (lectureEntity.getApplyCnt() >= 30) {
            throw new IllegalArgumentException("수강 신청 실패: 신청 인원 마감");
        }

        LectureEntity updatedLectureEntity = LectureEntity.builder()
                .lectureNo(lectureEntity.getLectureNo())
                .lectureName(lectureEntity.getLectureName())
                .applyCnt(lectureEntity.getApplyCnt() + 1)
                .build();
        jpaLectureRepository.save(updatedLectureEntity);
    }

}