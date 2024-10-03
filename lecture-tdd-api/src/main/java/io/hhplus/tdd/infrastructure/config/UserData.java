package io.hhplus.tdd.infrastructure.config;

import io.hhplus.tdd.domain.user.UserEntity;
import io.hhplus.tdd.infrastructure.repository.JpaUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserData implements CommandLineRunner {

    private final JpaUserRepository jpaUserRepository;

    public UserData(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (jpaUserRepository.count() == 0) {
            for(int i = 0; i < 45; i++) {
                UserEntity user = UserEntity.builder()
                        .userId("user" + i)
                        .userPw("pw" + i)
                        .userName("userName" + i)
                        .build();
                
                jpaUserRepository.save(user);
            }
            System.out.println("임시 사용자 추가 완료");
        } else {
            System.out.println("사용자가 존재함");
        }
    }
}
