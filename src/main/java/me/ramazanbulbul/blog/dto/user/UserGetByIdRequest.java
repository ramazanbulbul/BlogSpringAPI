package me.ramazanbulbul.blog.dto.user;

import lombok.Data;

@Data
public class UserGetByIdRequest {
    Long userId;
    public UserGetByIdRequest(Long userId){
        this.userId = userId;
    }
}
