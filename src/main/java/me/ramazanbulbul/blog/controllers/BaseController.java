package me.ramazanbulbul.blog.controllers;

import me.ramazanbulbul.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
public class BaseController {
    @Autowired
    public UserService userService;
    public BaseController(){

    }
}
