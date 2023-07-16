package me.ramazanbulbul.blog.services;

import me.ramazanbulbul.blog.entities.User;
import me.ramazanbulbul.blog.repos.UserRepository;
import me.ramazanbulbul.blog.utils.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserService extends BaseService{
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getAll(){
        return userRepository.getAllByIsDeleted(false);
    }
    public User add(User user){
        user.setPassword(Hash.text2MD5(user.getPassword()));
        return userRepository.save(user);
    }
    public User remove(Long userId){
        User user = userRepository.getById(userId);
        user.setDeleted(true);
        user.setDeletedTimezone(new Date());
        return userRepository.save(user);
    }

}
