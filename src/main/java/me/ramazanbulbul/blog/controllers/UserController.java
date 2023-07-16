package me.ramazanbulbul.blog.controllers;

import jakarta.persistence.criteria.CriteriaBuilder;
import me.ramazanbulbul.blog.entities.User;
import me.ramazanbulbul.blog.utils.Hash;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController extends BaseController{

    @RequestMapping("/GetAll")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping("/Add")
    public ResponseEntity<User> add(@RequestBody User user){
        return ResponseEntity.ok(userService.add(user));
    }
    @PutMapping("/Remove/{userId}")
    public ResponseEntity<User> remove(@PathVariable Long userId){
        return ResponseEntity.ok(userService.remove(userId));
    }

}
