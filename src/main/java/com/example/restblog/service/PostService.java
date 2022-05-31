package com.example.restblog.service;

import com.example.restblog.data.*;
import com.example.restblog.dto.CreatePostDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoriesRepository categoriesRepository;
    private final UserService userService;

    public PostService(
            PostRepository postRepository,
            CategoriesRepository categoriesRepository,
            UserService userService){
        this.postRepository = postRepository;
        this.categoriesRepository = categoriesRepository;
        this.userService = userService;
    }

    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postRepository.searchByTitleLike(keyword);
    }

    public void addPost(CreatePostDto dto, Post newPost, String username) {

        User user = userService.getUserByUsername(username);

        newPost.setTitle(dto.getTitle());
        newPost.setContent(dto.getContent());
        user.getPosts().add(newPost);
        newPost.setUser(user);

        List<Category> categoriesToAdd = new ArrayList<>();

        for (String categoryName : dto.getCategories()) {
            categoriesToAdd.add(categoriesRepository.findCategoryByName(categoryName));
        }

        newPost.setCategories(categoriesToAdd);
        postRepository.save(newPost);
    }

    public void updatePost(long postId, Post post) {
        Post postToUpdate = postRepository.findById(postId).orElseThrow();

        if (post.getContent() != null && !post.getContent().isEmpty()) {
            postToUpdate.setContent(post.getContent());
        }
        if (post.getTitle() != null && !post.getTitle().isEmpty()) {
            postToUpdate.setTitle(post.getTitle());
        }
        postRepository.save(postToUpdate);
    }

    public void deletePostById(long id) {
        postRepository.deleteById(id);
    }
}