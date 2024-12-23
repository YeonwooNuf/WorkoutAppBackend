package com.example.workoutbackend.controller;

import com.example.workoutbackend.dto.UserPlanDto;
import com.example.workoutbackend.service.UserPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-plans")
public class UserPlanController {

    @Autowired
    private UserPlanService userPlanService;

    // 사용자별 선택된 플랜 조회
    @GetMapping("/{username}")
    public Optional<UserPlanDto> getSelectedPlan(@PathVariable String username) {
        return userPlanService.getSelectedPlanForUser(username);
    }

    // 사용자별 선택된 플랜 저장
    @PostMapping("/{username}/select")
    public UserPlanDto selectPlan(@PathVariable String username, @RequestParam Long planId) {
        return userPlanService.saveUserPlan(username, planId);
    }
}
