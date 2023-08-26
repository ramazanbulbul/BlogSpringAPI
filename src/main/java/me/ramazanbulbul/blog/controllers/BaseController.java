package me.ramazanbulbul.blog.controllers;

import me.ramazanbulbul.blog.services.PostService;
import me.ramazanbulbul.blog.services.ResponseService;
import me.ramazanbulbul.blog.services.RoleService;
import me.ramazanbulbul.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
public class BaseController {
    @Autowired
    public UserService userService;
    @Autowired
    public ResponseService responseService;
    @Autowired
    public PostService postService;
    public BaseController(){

    }
}
