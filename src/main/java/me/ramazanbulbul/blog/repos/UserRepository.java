package me.ramazanbulbul.blog.repos;

import me.ramazanbulbul.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getAllByIsDeleted(boolean isDeleted);
    User getByUserId(Long userId);
    User getByUserIdAndIsDeleted(Long userId, boolean isDeleted);
    User getByUsernameAndIsDeleted(String username, boolean isDeleted);
    User getByEmailAndIsDeleted(String email, boolean isDeleted);

}
