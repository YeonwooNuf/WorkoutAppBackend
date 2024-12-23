package com.example.workoutbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workouts")
public class WorkoutDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutId; // 운동 고유 식별자 (기본키), auto_increment 설정된 컬럼.

    private String workoutName; // 운동 이름
    private String guide; // 운동 방법(가이드)
    private String bodyPart; // 운동 부위

    private String workoutImagePath; // 운동 이미지 경로

    // 기본 생성자
    public WorkoutDao() {
    }

    // 생성자
    public WorkoutDao(String workoutName, String guide, String bodyPart, String workoutImagePath) {
        this.workoutName = workoutName;
        this.guide = guide;
        this.bodyPart = bodyPart;
        this.workoutImagePath = workoutImagePath;
    }

    // Getter and Setter
    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getWorkoutImagePath() {
        return workoutImagePath;
    }

    public void setWorkoutImagePath(String workoutImagePath) {
        this.workoutImagePath = workoutImagePath;
    }
}
