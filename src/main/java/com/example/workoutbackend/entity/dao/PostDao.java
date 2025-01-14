package com.example.workoutbackend.entity.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class PostDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String content;

    @Column(name = "post_image_url") // 게시글 이미지 URL
    private String postImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDao author;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public UserDao getAuthor() {
        return author;
    }

    public void setAuthor(UserDao author) {
        this.author = author;
    }
}
