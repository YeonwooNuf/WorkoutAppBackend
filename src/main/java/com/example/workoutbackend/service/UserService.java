package com.example.workoutbackend.service;

import com.example.workoutbackend.entity.dao.UserDao;
import com.example.workoutbackend.entity.dto.UserDto;
import com.example.workoutbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDao registerUser(UserDto userDto) {
        UserDao user = new UserDao();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setGender(userDto.getGender());
        user.setBirthDate(userDto.getBirthDate());

        return userRepository.save(user);
    }

    public UserDto getUserByUsername(String username) {
        Optional<UserDao> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            UserDao userDao = user.get();
            UserDto dto = new UserDto();
            dto.setUserId(userDao.getUserId());
            dto.setName(userDao.getName());
            dto.setEmail(userDao.getEmail());
            dto.setUsername(userDao.getUsername());
            dto.setPhoneNumber(userDao.getPhoneNumber());
            dto.setGender(userDao.getGender());
            dto.setBirthDate(userDao.getBirthDate());
            return dto;
        }
        return null;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserDto dto = new UserDto();
            dto.setUserId(user.getUserId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setUsername(user.getUsername());
            dto.setPhoneNumber(user.getPhoneNumber());
            dto.setGender(user.getGender());
            dto.setBirthDate(user.getBirthDate());
            return dto;
        }).collect(Collectors.toList());
    }

    // 사용자 유효성 검사 메서드
    public boolean validateUser(String username, String password) {
        Optional<UserDao> user = userRepository.findByUsername(username);
        // 사용자 이름과 비밀번호가 일치하는지 확인
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public boolean deleteUserByUsername(String username) {
        Optional<UserDao> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        } else {
            return false;
        }
    }
}