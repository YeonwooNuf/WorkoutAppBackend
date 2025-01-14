package com.example.workoutbackend.controller;

import com.example.workoutbackend.entity.dto.WorkoutDto;
import com.example.workoutbackend.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<WorkoutDto> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addWorkout(
            @RequestParam("workoutName") String workoutName,
            @RequestParam("guide") String guide,
            @RequestParam("bodyPart") String bodyPart,
            @RequestParam("workoutImage") MultipartFile workoutImage) {
        workoutService.addWorkout(workoutName, guide, bodyPart, workoutImage);
        return ResponseEntity.ok("Workout added successfully!");
    }

    // 추가적인 메서드들(예: 수정, 삭제) 필요 시 작성
}
