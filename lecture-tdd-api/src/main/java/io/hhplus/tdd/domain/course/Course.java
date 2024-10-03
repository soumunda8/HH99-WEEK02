package io.hhplus.tdd.domain.course;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Course {

    private Long courseNo;
    private Long lectureNo;
    private Long userId;
    private LocalDateTime createDate;

}