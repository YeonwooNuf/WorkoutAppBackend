package com.example.workoutbackend.repository;

import com.example.workoutbackend.entity.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDao, Long> {
    // 사용자 이름으로 사용자 찾기
    Optional<UserDao> findByUsername(String username);

    // 사용자 이름이 존재하는지 확인
    boolean existsByUsername(String username);

    // 이메일이 존재하는지 확인
    boolean existsByEmail(String email);
}
