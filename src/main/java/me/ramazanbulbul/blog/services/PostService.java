package me.ramazanbulbul.blog.services;

import me.ramazanbulbul.blog.dto.dev.ResponseObject;
import me.ramazanbulbul.blog.dto.post.PostGetAllRequest;
import me.ramazanbulbul.blog.dto.post.PostGetByIdRequest;
import me.ramazanbulbul.blog.dto.post.*;
import me.ramazanbulbul.blog.dto.user.UserGetByIdRequest;
import me.ramazanbulbul.blog.entities.Post;
import me.ramazanbulbul.blog.entities.User;
import me.ramazanbulbul.blog.repos.PostRepository;
import me.ramazanbulbul.blog.utils.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService extends BaseService{

    @Autowired
    UserService userService;
    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    public ResponseObject<List<Post>, PostGetAllRequest> getAll(PostGetAllRequest request){
        ResponseObject<List<Post>, PostGetAllRequest> responseObject = new ResponseObject<List<Post>, PostGetAllRequest>(request);
        try {
            List<Post> posts = postRepository.getAllByIsDeleted(false);
            responseObject.setResponse(posts);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
    public ResponseObject<List<Post>, PostGetAllRequest> getAllWithDeleted(PostGetAllRequest request){
        ResponseObject<List<Post>, PostGetAllRequest> responseObject = new ResponseObject<List<Post>, PostGetAllRequest>(request);
        try {
            List<Post> posts = postRepository.findAll();
            responseObject.setResponse(posts);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
    public ResponseObject<Post, PostGetByIdRequest> getById(PostGetByIdRequest request){
        ResponseObject<Post, PostGetByIdRequest> responseObject = new ResponseObject<Post, PostGetByIdRequest>(request);
        try {
            Long postId = request.getPostId();
            Post post = postRepository.getByPostIdAndIsDeleted(postId, false);
            responseObject.setResponse(post);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
    public ResponseObject<Post, PostAddRequest> add(PostAddRequest request){
        ResponseObject<Post, PostAddRequest> responseObject = new ResponseObject<Post, PostAddRequest>(request);
        try {
            Post post = request.getPost();
            User createdUser = request.getCreatedUser();

            if (userService.getById(new UserGetByIdRequest(createdUser.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Oluşturan kullanıcı bulunamadı.");
                return responseObject;
            }
            post.setCreatedUser(createdUser);
            post.setCreatedTimestamp(new Date());
            Post responsePost = postRepository.save(post);
            responseObject.setResponse(responsePost);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getLocalizedMessage());
        }
        return responseObject;
    }
    public ResponseObject<Post, PostUpdateRequest> update(PostUpdateRequest request){
        ResponseObject<Post, PostUpdateRequest> responseObject = new ResponseObject<Post, PostUpdateRequest>(request);
        try {
            Post post = request.getPost();
            Post newPost = request.getNewPost();
            User updatedUser = request.getUpdatedUser();
            if (getById(new PostGetByIdRequest(post.getPostId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Güncellenecek post bulunamadı.");
                return responseObject;
            }
            if (userService.getById(new UserGetByIdRequest(updatedUser.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Güncelleyen kullanıcı bulunamadı.");
                return responseObject;
            }
            if (post.getPostId().equals(newPost.getPostId())){
                post = newPost;

                post.setUpdatedUser(updatedUser);
                post.setUpdatedTimestamp(new Date());
                Post responsePost = postRepository.save(post);

                responseObject.setResponse(responsePost);
            }else {
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Post idleri eşleşmemektedir.");
            }
            return responseObject;
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }

    public ResponseObject<Post, PostDeleteRequest> delete(PostDeleteRequest request){
        ResponseObject<Post, PostDeleteRequest> responseObject = new ResponseObject<Post, PostDeleteRequest>(request);
        try {
            Post post = getById(new PostGetByIdRequest(request.getPost().getPostId())).getResponse();
            User deletedUser = request.getDeletedUser();

            if (this.getById(new PostGetByIdRequest(post.getPostId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Güncellenecek post bulunamadı.");
                return responseObject;
            }
            if (userService.getById(new UserGetByIdRequest(deletedUser.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Silmeye çalışan kullanıcı bulunamadı.");
                return responseObject;
            }

            post.setDeletedUser(deletedUser);
            post.setDeleted(true);
            post.setDeletedTimestamp(new Date());
            postRepository.save(post);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
}
