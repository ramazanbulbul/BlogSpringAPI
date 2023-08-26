package me.ramazanbulbul.blog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import me.ramazanbulbul.blog.dto.dev.ResponseObject;
import me.ramazanbulbul.blog.dto.post.PostAddRequest;
import me.ramazanbulbul.blog.dto.post.PostDeleteRequest;
import me.ramazanbulbul.blog.dto.post.PostGetAllRequest;
import me.ramazanbulbul.blog.dto.post.PostUpdateRequest;
import me.ramazanbulbul.blog.dto.response.ResponseAddRequest;
import me.ramazanbulbul.blog.entities.Post;
import me.ramazanbulbul.blog.utils.JsonUtility;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Post")
public class PostController extends BaseController {
    @RequestMapping("/GetAll")
    public ResponseObject<List<Post>, PostGetAllRequest> getAll(@RequestBody PostGetAllRequest request, HttpServletRequest httpRequest){
        ResponseObject<List<Post>, PostGetAllRequest> response = postService.getAll(request);
        responseService.add(new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }

    @RequestMapping("/GetAllWithDeleted")
    public ResponseObject<List<Post>, PostGetAllRequest> getAllWithDeleted(@RequestBody PostGetAllRequest request, HttpServletRequest httpRequest){
        ResponseObject<List<Post>, PostGetAllRequest> response = postService.getAllWithDeleted(request);
        responseService.add( new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }
    @RequestMapping("/Add")
    public ResponseObject<Post, PostAddRequest> add(@RequestBody PostAddRequest request, HttpServletRequest httpRequest){
        ResponseObject<Post, PostAddRequest> response = postService.add(request);
        responseService.add( new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }
    @RequestMapping("/Update")
    public ResponseObject<Post, PostUpdateRequest> delete(@RequestBody PostUpdateRequest request, HttpServletRequest httpRequest){
        ResponseObject<Post, PostUpdateRequest> response = postService.update(request);
        responseService.add( new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }
    @RequestMapping("/Delete")
    public ResponseObject<Post, PostDeleteRequest> delete(@RequestBody PostDeleteRequest request, HttpServletRequest httpRequest){
        ResponseObject<Post, PostDeleteRequest> response = postService.delete(request);
        responseService.add( new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }
}
