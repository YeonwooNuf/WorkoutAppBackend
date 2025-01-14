package com.example.workoutbackend.entity.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "user_plans")
public class UserPlanDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPlanId; // 사용자 플랜 매핑 테이블의 기본키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // 사용자 ID를 외래키로 설정
    private UserDao user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false) // 플랜 ID를 외래키로 설정
    private PlanDao plan;

    public UserPlanDao() {}

    public UserPlanDao(UserDao user, PlanDao plan) {
        this.user = user;
        this.plan = plan;
    }

    public Long getUserPlanId() {
        return userPlanId;
    }

    public void setUserPlanId(Long userPlanId) {
        this.userPlanId = userPlanId;
    }

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }

    public PlanDao getPlan() {
        return plan;
    }

    public void setPlan(PlanDao plan) {
        this.plan = plan;
    }
}
