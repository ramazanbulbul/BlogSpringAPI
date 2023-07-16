package me.ramazanbulbul.blog.repos;

import me.ramazanbulbul.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getMD5PasswordByUsername(String username);
    List<User> getAllByIsDeleted(boolean isDeleted);
}
