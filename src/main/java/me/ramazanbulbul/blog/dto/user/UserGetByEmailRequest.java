package me.ramazanbulbul.blog.dto.user;

import lombok.Data;

@Data
public class UserGetByEmailRequest {
    String email;

    public UserGetByEmailRequest(String email) {
        this.email = email;
    }
}
