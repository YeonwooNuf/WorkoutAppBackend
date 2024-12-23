package com.example.workoutbackend.controller;

import com.example.workoutbackend.entity.PlanDto;
import com.example.workoutbackend.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping
    public List<PlanDto> getAllPlans() {
        return planService.getAllPlans();
    }

    @PostMapping
    public PlanDto savePlan(@RequestBody PlanDto planDto) {
        return planService.savePlan(planDto);
    }

    @PutMapping("/{planId}/status")
    public PlanDto updatePlanStatus(@PathVariable Long planId, @RequestParam String status) {
        return planService.updatePlanStatus(planId, status);
    }
}
