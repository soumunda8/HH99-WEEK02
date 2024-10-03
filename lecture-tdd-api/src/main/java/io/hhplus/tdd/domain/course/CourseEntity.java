package io.hhplus.tdd.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseNo;

    @Column(nullable = false)
    private Long lectureNo;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

}