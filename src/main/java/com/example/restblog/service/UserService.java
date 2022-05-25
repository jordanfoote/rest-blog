package com.example.restblog.service;


import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CategoriesRepository categoriesRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository, CategoriesRepository categoriesRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    public void addPost(Post newPost, String username) {
        User user = getUserByUsername(username);
        user.getPosts().add(newPost);
        newPost.setUser(user);

        List<Category> categoriesToAdd = new ArrayList<>();

        for (Category category : newPost.getCategories()) {
            categoriesToAdd.add(categoriesRepository.findCategoryByName(category.getName()));
        }

        newPost.setCategories(categoriesToAdd);

        postRepository.save(newPost);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
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

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postRepository.searchByTitleLike(keyword);
    }
}
