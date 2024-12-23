package com.example.deliverybackend.service;

import com.example.deliverybackend.entity.UserDao;
import com.example.deliverybackend.entity.UserDto;
import com.example.deliverybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDto userDto) {
        // DTO에서 엔티티로 매핑하여 저장
        UserDao userdao = new UserDao();
        userdao.setUserNumber(userDto.getUserNumber());
        userdao.setUserId(userDto.getUserId());
        userdao.setPassword(userDto.getPassword());
        userdao.setName(userDto.getName());
        userdao.setPhone(userDto.getPhone());
        userdao.setEmail(userDto.getEmail());
        userRepository.save(userdao);
    }


    public UserDao findById(Long userNumber) {
        return userRepository.findById(userNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }





    public List<UserDao> getAllUsers() {
        return userRepository.findAll();
    }
}
