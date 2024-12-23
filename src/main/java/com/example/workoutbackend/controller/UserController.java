package com.example.deliverybackend.controller;

import com.example.deliverybackend.entity.UserDao;
import com.example.deliverybackend.entity.UserDto;
import com.example.deliverybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//클라이언트에서 받아서 DB에 저장
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save-users")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto); // UserService를 통해 유저 저장
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
//클라이언트로 user테이블 정보 전달
    @GetMapping("/allUsers") // 경로 변경
    public ResponseEntity<List<UserDao>> getAllUsers() { // 반환 타입 변경
        List<UserDao> users = userService.getAllUsers(); // 변경된 메소드 호출
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
