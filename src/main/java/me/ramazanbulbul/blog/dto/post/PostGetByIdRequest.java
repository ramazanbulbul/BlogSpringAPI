package me.ramazanbulbul.blog.dto.post;

import lombok.Data;

@Data
public class PostGetByIdRequest {
    Long postId;
    public PostGetByIdRequest(Long postId){
        this.postId = postId;
    }
}
