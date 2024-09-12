package com.example.codingShuttle.SecurityApp.Security_Application.serviceImpl;


import com.example.codingShuttle.SecurityApp.Security_Application.dto.PostDto;
import com.example.codingShuttle.SecurityApp.Security_Application.entities.PostEntity;
import com.example.codingShuttle.SecurityApp.Security_Application.entities.UserEntity;
import com.example.codingShuttle.SecurityApp.Security_Application.exceptions.ResourceNotFoundException;
import com.example.codingShuttle.SecurityApp.Security_Application.repositories.PostRepository;
import com.example.codingShuttle.SecurityApp.Security_Application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPosts) {
        PostEntity postEntity = modelMapper.map(inputPosts, PostEntity.class);
        PostEntity savedPostEntity = postRepository.save(postEntity);
        return modelMapper.map(savedPostEntity, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found with id " + postId));
        return modelMapper.map(postEntity, PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto inputPosts, Long postId) {
        PostEntity olderPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found with id " + postId));
        inputPosts.setId(postId);
        modelMapper.map(inputPosts, olderPost);
        PostEntity savedPostEntity = postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity, PostDto.class);
    }



}
