package com.example.workoutbackend.entity.dto;

public class UserPlanDto {

    private Long userPlanId;
    private Long userId;
    private Long planId;
    private String planName;  // 클라이언트에게 전송할 플랜명
    private int workoutDays;  // 클라이언트에게 전송할 운동일수

    public UserPlanDto() {}

    public UserPlanDto(Long userPlanId, Long userId, Long planId, String planName, int workoutDays) {
        this.userPlanId = userPlanId;
        this.userId = userId;
        this.planId = planId;
        this.planName = planName;
        this.workoutDays = workoutDays;
    }

    public Long getUserPlanId() {
        return userPlanId;
    }

    public void setUserPlanId(Long userPlanId) {
        this.userPlanId = userPlanId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getWorkoutDays() {
        return workoutDays;
    }

    public void setWorkoutDays(int workoutDays) {
        this.workoutDays = workoutDays;
    }
}
