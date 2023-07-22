package me.ramazanbulbul.blog.dto.user;

import lombok.Data;
import me.ramazanbulbul.blog.entities.User;

@Data
public class UserDeleteRequest {
    User user;
    User deletedUser;
}
