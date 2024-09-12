package com.example.codingShuttle.SecurityApp.Security_Application.service;


import com.example.codingShuttle.SecurityApp.Security_Application.dto.PostDto;

import java.util.List;

public interface PostService {
    
    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto inputPosts);

    PostDto getPostById(Long postId);

    PostDto updatePost(PostDto inputPosts, Long postId);


}
