package com.example.workoutbackend.entity;

public class WorkoutDto {
    private Long workoutId;
    private String workoutName;
    private String guide;
    private String bodyPart;
    private String workoutImagePath;

    // 생성자
    public WorkoutDto(Long workoutId, String workoutName, String guide, String bodyPart, String workoutImagePath) {
        this.workoutId = workoutId;
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
