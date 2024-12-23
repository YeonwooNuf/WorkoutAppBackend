package com.example.workoutbackend.service;

import com.example.workoutbackend.entity.WorkoutDto;
import com.example.workoutbackend.entity.WorkoutDao;
import com.example.workoutbackend.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<WorkoutDto> getAllWorkouts() {
        return workoutRepository.findAll().stream().map(workout -> new WorkoutDto(
                workout.getWorkoutId(),
                workout.getWorkoutName(),
                workout.getGuide(),
                workout.getBodyPart(),
                workout.getWorkoutImagePath()
        )).collect(Collectors.toList());
    }

    // 운동 추가하기
    public void addWorkout(String workoutName, String guide, String bodyPart, MultipartFile workoutImage) {
        String imagePath = saveImage(workoutImage); // 이미지를 서버에 저장하고 경로를 반환
        WorkoutDao workout = new WorkoutDao(workoutName, guide, bodyPart, imagePath);
        workoutRepository.save(workout);
    }

    private String saveImage(MultipartFile workoutImage) {
        // 파일 이름을 유니크하게 만들기 위해 UUID 사용
        String fileName = UUID.randomUUID().toString() + "_" + workoutImage.getOriginalFilename().replaceAll(" ", "_");

        // 이미지 저장 경로 설정 (static/images 폴더에 저장)
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";

        Path uploadPath = Paths.get(uploadDir);
        // 경로가 없으면 생성
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create directory for image storage", e);
            }
        }

        // 파일을 저장할 위치 설정
        Path filePath = uploadPath.resolve(fileName);
        try {
            // 파일 저장
            workoutImage.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }

        // 반환 경로를 프론트엔드에서 접근 가능한 URL로 설정 (예: /images/filename.png)
        return "/images/" + fileName;
    }
}
