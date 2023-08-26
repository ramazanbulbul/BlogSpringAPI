package me.ramazanbulbul.blog.services;

import me.ramazanbulbul.blog.BlogApplication;
import me.ramazanbulbul.blog.repos.PostRepository;
import me.ramazanbulbul.blog.repos.ResponseRepository;
import me.ramazanbulbul.blog.repos.RoleRepository;
import me.ramazanbulbul.blog.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ResponseRepository responseRepository;
    @Autowired
    PostRepository postRepository;
    public BaseService(){

    }
}
