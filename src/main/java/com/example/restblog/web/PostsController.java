package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    @GetMapping
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "First Post", "This is my very first post!"));
        posts.add(new Post(2L, "Another Post", "This is another post."));
        return posts;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {
        return new Post(id, "First Post", "This is my very first post!");
    }
}
