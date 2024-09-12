package com.example.codingShuttle.SecurityApp.Security_Application.controller;


import com.example.codingShuttle.SecurityApp.Security_Application.dto.PostDto;
import com.example.codingShuttle.SecurityApp.Security_Application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPosts){
        return postService.createNewPost(inputPosts);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto inputPosts, @PathVariable Long postId){
       return postService.updatePost(inputPosts,postId);
    }


    }

