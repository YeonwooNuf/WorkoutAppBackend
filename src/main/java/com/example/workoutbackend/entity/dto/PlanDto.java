package com.example.workoutbackend.entity.dto;

public class PlanDto {

    private Long planId;
    private int workoutDays;
    private String planName;
    private String planStatus;

    public PlanDto() {}

    public PlanDto(Long planId, int workoutDays, String planName, String planStatus) {
        this.planId = planId;
        this.workoutDays = workoutDays;
        this.planName = planName;
        this.planStatus = planStatus;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public int getWorkoutDays() {
        return workoutDays;
    }

    public void setWorkoutDays(int workoutDays) {
        this.workoutDays = workoutDays;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }
}
