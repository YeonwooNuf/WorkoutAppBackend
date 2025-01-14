package com.example.workoutbackend.repository;

import com.example.workoutbackend.entity.dao.PostDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostDao, Long> {
    List<PostDao> findByAuthorUserId(Long userId); // 특정 사용자의 게시글 조회
}
