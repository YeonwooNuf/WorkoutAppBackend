package com.example.workoutbackend.controller;

import com.example.workoutbackend.entity.dto.PostDto;
import com.example.workoutbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 게시글 생성
    @PostMapping("/{userId}")
    public ResponseEntity<PostDto> createPost(
            @PathVariable Long userId,
            @RequestParam("content") String content, // 텍스트 데이터
            @RequestParam(value = "image", required = false) MultipartFile image) { // 이미지 파일
        PostDto createdPost = postService.createPost(userId, content, image);
        return ResponseEntity.ok(createdPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }


    // 특정 사용자의 게시글 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long userId) {
        List<PostDto> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }
}
