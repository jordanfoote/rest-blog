package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    List<Post> posts = setPostList();

    @GetMapping
    public List<Post> getAll() {
        return posts;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {
        for (Post post : getAll()) {
            if (Objects.equals(post.getId(), id)) {
                return post;
            }
        }
        return new Post();
    }

    @PostMapping
    private void createPost(@RequestBody Post post) {
        System.out.println(post);
    }

    @PutMapping("{id}")
    private void updatePost(@RequestBody Post updatedPost, @PathVariable Long id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                post.setContent(updatedPost.getContent());
                post.setTitle(updatedPost.getTitle());
            }
        }
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable Long id) {
        System.out.println("Deleting: " + id);
    }

    private List<Post> setPostList() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "First Post", "This is my very first post!"));
        posts.add(new Post(2L, "Another Post", "This is another post."));
        return posts;
    }
}