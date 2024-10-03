package io.hhplus.tdd.domain.lecture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String applyStartDate;

    @Column(nullable = false)
    private String applyEndDate;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer applyCnt;

}
