package com.example.workoutbackend.controller;

import com.example.workoutbackend.entity.dao.UserDao;
import com.example.workoutbackend.entity.dto.UserDto;
import com.example.workoutbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        if (userService.getUserByUsername(userDto.getUsername()) != null) {
            return ResponseEntity.badRequest().body("이미 사용중인 아이디 입니다.");
        }
        userService.registerUser(userDto);
        return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        UserDto user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 로그인 엔드포인트 추가
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        UserDao user = userService.findByUsername(userDto.getUsername());

        if (user != null && userService.validateUser(userDto.getUsername(), userDto.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getUserId()); // ✅ userId 반환
            response.put("username", user.getUsername()); // ✅ username 반환
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }
    }

    // 계정 삭제 API
    @DeleteMapping("/{username}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable String username) {
        boolean isDeleted = userService.deleteUserByUsername(username);
        if (isDeleted) {
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }
}
