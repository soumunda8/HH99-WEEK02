package io.hhplus.tdd.domain.lecture;

import lombok.Data;

@Data
public class Lecture {

    private Long lectureNo;
    private String lectureName;
    private String startDate;
    private String applyStartDate;
    private String applyEndDate;
    private Integer applyCnt;

}
