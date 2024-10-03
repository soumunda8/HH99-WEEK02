package io.hhplus.tdd.domain.user;

import lombok.Data;

@Data
public class User {

    private Long userNo;
    private String userId;
    private String userPw;
    private String userName;

}