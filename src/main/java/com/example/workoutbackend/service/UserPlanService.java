package com.example.workoutbackend.service;

import com.example.workoutbackend.dto.UserPlanDto;
import com.example.workoutbackend.entity.PlanDao;
import com.example.workoutbackend.entity.UserPlanDao;
import com.example.workoutbackend.entity.UserDao;
import com.example.workoutbackend.repository.PlanRepository;
import com.example.workoutbackend.repository.UserPlanRepository;
import com.example.workoutbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPlanService {

    @Autowired
    private UserPlanRepository userPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    // 사용자별 선택된 플랜 조회 (DTO로 변환하여 반환)
    public Optional<UserPlanDto> getSelectedPlanForUser(String username) {
        Optional<UserDao> user = userRepository.findByUsername(username);
        return user.flatMap(userPlanRepository::findByUser).map(this::convertToDto);
    }

    // 사용자별 선택된 플랜 저장
    public UserPlanDto saveUserPlan(String username, Long planId) {
        UserDao user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        PlanDao plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // 기존에 선택된 플랜이 있는 경우 업데이트, 없으면 새로 저장
        UserPlanDao userPlan = userPlanRepository.findByUser(user)
                .orElse(new UserPlanDao(user, plan));

        userPlan.setPlan(plan);
        UserPlanDao savedUserPlan = userPlanRepository.save(userPlan);
        return convertToDto(savedUserPlan);
    }

    // Entity to DTO 변환
    private UserPlanDto convertToDto(UserPlanDao userPlanDao) {
        return new UserPlanDto(
                userPlanDao.getUserPlanId(),
                userPlanDao.getUser().getUserId(),
                userPlanDao.getPlan().getPlanId(),
                userPlanDao.getPlan().getPlanName(),
                userPlanDao.getPlan().getWorkoutDays()
        );
    }
}
