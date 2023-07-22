package me.ramazanbulbul.blog.dto.user;

import lombok.Data;

@Data
public class UserGetByUsernameRequest {
    String username;

    public UserGetByUsernameRequest(String username) {
        this.username = username;
    }
}
