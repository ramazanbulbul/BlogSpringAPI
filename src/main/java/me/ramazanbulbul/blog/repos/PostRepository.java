package me.ramazanbulbul.blog.repos;

import me.ramazanbulbul.blog.entities.Post;
import me.ramazanbulbul.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getAllByIsDeleted(boolean isDeleted);
    Post getByPostId(Long userId);
    Post getByPostIdAndIsDeleted(Long postId, boolean isDeleted);
}
