package com.example.workoutbackend.repository;

import com.example.workoutbackend.entity.dao.WorkoutDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutDao, Long> {
}
