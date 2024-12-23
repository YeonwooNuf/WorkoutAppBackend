package com.example.workoutbackend.service;

import com.example.workoutbackend.entity.PlanDto;
import com.example.workoutbackend.entity.PlanDao;
import com.example.workoutbackend.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    // 모든 플랜 조회
    public List<PlanDto> getAllPlans() {
        return planRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 플랜 저장
    public PlanDto savePlan(PlanDto planDto) {
        PlanDao planDao = convertToDao(planDto);
        PlanDao savedPlan = planRepository.save(planDao);
        return convertToDto(savedPlan);
    }

    // 플랜 상태 업데이트
    public PlanDto updatePlanStatus(Long planId, String status) {
        PlanDao plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        plan.setPlanStatus(status);
        PlanDao updatedPlan = planRepository.save(plan);
        return convertToDto(updatedPlan);
    }

    // Entity to DTO 변환
    private PlanDto convertToDto(PlanDao planDao) {
        return new PlanDto(planDao.getPlanId(), planDao.getWorkoutDays(), planDao.getPlanName(), planDao.getPlanStatus());
    }

    // DTO to Entity 변환
    private PlanDao convertToDao(PlanDto planDto) {
        return new PlanDao(planDto.getWorkoutDays(), planDto.getPlanName(), planDto.getPlanStatus());
    }
}
