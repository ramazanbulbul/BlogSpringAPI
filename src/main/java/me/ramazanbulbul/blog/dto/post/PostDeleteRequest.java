package me.ramazanbulbul.blog.dto.post;

import lombok.Data;
import me.ramazanbulbul.blog.entities.Post;
import me.ramazanbulbul.blog.entities.User;

@Data
public class PostDeleteRequest {
    Post post;
    User deletedUser;
}