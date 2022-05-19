package com.example.restblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping({"/", "/about", "/posts", "/login", "/home", "/register", "/user"})
    public String showView() {
        return "forward:/index.html";
    }
}
