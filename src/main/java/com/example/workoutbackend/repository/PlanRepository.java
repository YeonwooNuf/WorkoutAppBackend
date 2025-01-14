package com.example.workoutbackend.repository;

import com.example.workoutbackend.entity.dao.PlanDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<PlanDao, Long> {
    List<PlanDao> findByPlanStatus(String planStatus);
}
