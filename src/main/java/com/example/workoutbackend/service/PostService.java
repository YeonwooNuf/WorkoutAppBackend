package com.example.workoutbackend.service;

import com.example.workoutbackend.entity.dao.PostDao;
import com.example.workoutbackend.entity.dao.UserDao;
import com.example.workoutbackend.entity.dto.PostDto;
import com.example.workoutbackend.repository.PostRepository;
import com.example.workoutbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    private String saveImage(MultipartFile image) {
        try {
            // 업로드 디렉토리 경로 설정
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 고유한 파일명 생성
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // 이미지 저장
            Files.copy(image.getInputStream(), filePath);

            // 클라이언트에서 접근 가능한 URL 반환
            return "/uploads/" + fileName; // 상대 경로 반환
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
        dto.setPostImageUrl(post.getPostImageUrl()); // 상대 경로 설정
        dto.setAuthorId(post.getAuthor().getUserId());
        dto.setAuthorUsername(post.getAuthor().getUsername());
        return dto;
    }
}
