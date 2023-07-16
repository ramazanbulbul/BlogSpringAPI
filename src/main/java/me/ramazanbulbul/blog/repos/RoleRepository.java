package me.ramazanbulbul.blog.repos;

import me.ramazanbulbul.blog.entities.Role;
import me.ramazanbulbul.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository  extends JpaRepository<Role, Long> {
    List<Role> getAllByIsDeleted(boolean isDeleted);
}
