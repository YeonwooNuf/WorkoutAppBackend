package com.example.workoutbackend.entity.dao;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 사용자 고유 식별자 (기본키)

    @Column(nullable = false)
    private String name; // 사용자 이름

    @Column(nullable = false, unique = true)
    private String email; // 사용자 이메일 (유니크 설정)

    @Column(nullable = false, unique = true)
    private String username; // 사용자 아이디 (유니크 설정)

    @Column(nullable = false)
    private String password; // 사용자 비밀번호

    @Column
    private String phoneNumber; // 사용자 전화번호

    @Column
    private String gender; // 사용자 성별

    @Column
    private LocalDate birthDate; // 사용자 생년월일

    @Column(name = "profile_image_url")
    private String profileImageUrl; // 프로필 이미지 URL

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostDao> posts; // 사용자가 작성한 게시글 리스트

    // Getter and Setter methods
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public List<PostDao> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDao> posts) {
        this.posts = posts;
    }
}
