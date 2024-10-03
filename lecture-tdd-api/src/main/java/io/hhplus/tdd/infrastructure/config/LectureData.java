package io.hhplus.tdd.infrastructure.config;

import io.hhplus.tdd.domain.lecture.LectureEntity;
import io.hhplus.tdd.infrastructure.repository.JpaLectureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LectureData implements CommandLineRunner {

    private final JpaLectureRepository jpaLectureRepository;

    public LectureData(JpaLectureRepository jpaLectureRepository) {
        this.jpaLectureRepository = jpaLectureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(jpaLectureRepository.count() == 0) {
            String[] lectureNames = {
                    "트렌드 코리아 2025", "고양이 해결사 깜냥 7", "하루 한 장 나의 어휘력을 위한 필사 노트", "고전이 답했다 마땅히 살아야 할 삶에 대하여",
                    "언젠가 우리가 같은 별을 바라본다면", "생각의 연금술", "흔한남매 17", "시대예보 : 호명사회",
                    "영원한 천국", "너에게 들려주는 단단한 말"
            };
            for(int i = 0; i < 10; i++) {
                LectureEntity lecture = LectureEntity.builder()
                        .lectureName(lectureNames[i])
                        .startDate("2024-10-30")
                        .applyStartDate("2024-10-04")
                        .applyEndDate("2024-10-17")
                        .applyCnt(0)
                        .build();

                jpaLectureRepository.save(lecture);
            }
            System.out.println("임의 강의를 추가함");
        } else {
            System.out.println("강의가 존재함");
        }
    }

}