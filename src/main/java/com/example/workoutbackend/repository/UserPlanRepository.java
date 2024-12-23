package com.example.workoutbackend.repository;

import com.example.workoutbackend.entity.UserPlanDao;
import com.example.workoutbackend.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlanDao, Long> {
    Optional<UserPlanDao> findByUser(UserDao user);
}
