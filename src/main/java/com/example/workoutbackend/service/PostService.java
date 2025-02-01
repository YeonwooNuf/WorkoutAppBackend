package com.example.workoutbackend.service;

import com.example.workoutbackend.entity.dao.PostDao;
import com.example.workoutbackend.entity.dao.UserDao;
import com.example.workoutbackend.entity.dto.PostDto;
import com.example.workoutbackend.repository.PostRepository;
import com.example.workoutbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public PostDto createPost(Long userId, String content, MultipartFile image) {
        UserDao user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = saveImage(image);
        }

        PostDao post = new PostDao();
        post.setContent(content);
        post.setPostImageUrl(imageUrl);
        post.setAuthor(user);

        PostDao savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    // 로컬 환경에서 게시글 사진을 받기 위함.
    private String saveImage(MultipartFile image) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

//          String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            String fileName = image.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // ✅ 절대 경로 반환 (Flutter에서 접근 가능)

            // ✅ 파일이 이미 존재하면 새로 저장하지 않고 기존 경로 반환
            if (Files.exists(filePath)) {
                System.out.println("이미 존재하는 파일입니다: " + fileName);
                return "http://10.0.2.2:8080/uploads/" + fileName;
            }

            // ✅ 파일이 존재하지 않는 경우만 저장
            Files.copy(image.getInputStream(), filePath);
            return "http://10.0.2.2:8080/uploads/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }


    public List<PostDto> getPostsByUserId(Long userId) {
        return postRepository.findByAuthorUserId(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deletePost(Long postId) {
        PostDao post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        postRepository.delete(post);
    }

    private PostDto mapToDto(PostDao post) {
        PostDto dto = new PostDto();
        dto.setPostId(post.getPostId());
        dto.setContent(post.getContent());
        dto.setPostImageUrl(post.getPostImageUrl());
        dto.setAuthorId(post.getAuthor().getUserId());
        dto.setAuthorUsername(post.getAuthor().getUsername());
        return dto;
    }
}