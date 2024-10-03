package io.hhplus.tdd.domain.lecture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lecture")
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureNo;

    @Column(nullable = false)
    private String lectureName;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer applyCnt;

}
