package me.ramazanbulbul.blog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import me.ramazanbulbul.blog.dto.dev.ResponseObject;
import me.ramazanbulbul.blog.dto.response.ResponseAddRequest;
import me.ramazanbulbul.blog.dto.user.UserAddRequest;
import me.ramazanbulbul.blog.dto.user.UserDeleteRequest;
import me.ramazanbulbul.blog.dto.user.UserGetAllRequest;
import me.ramazanbulbul.blog.dto.user.UserUpdateRequest;
import me.ramazanbulbul.blog.entities.User;
import me.ramazanbulbul.blog.utils.JsonUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController extends BaseController{

    @RequestMapping("/GetAll")
    public ResponseObject<List<User>, UserGetAllRequest> getAll(@RequestBody UserGetAllRequest request, HttpServletRequest httpRequest){
        ResponseObject<List<User>, UserGetAllRequest> response = userService.getAll(request);
        responseService.add(new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }

    @RequestMapping("/GetAllWithDeleted")
    public ResponseObject<List<User>, UserGetAllRequest> getAllWithDeleted(@RequestBody UserGetAllRequest request, HttpServletRequest httpRequest){
        ResponseObject<List<User>, UserGetAllRequest> response = userService.getAllWithDeleted(request);
        responseService.add( new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }

    @RequestMapping("/Add")
    public ResponseObject<User, UserAddRequest> add(@RequestBody UserAddRequest request, HttpServletRequest httpRequest){
        ResponseObject<User, UserAddRequest> response = userService.add(request);
        responseService.add( new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }
    @RequestMapping("/Update")
    public ResponseObject<User, UserUpdateRequest> delete(@RequestBody UserUpdateRequest request, HttpServletRequest httpRequest){
        ResponseObject<User, UserUpdateRequest> response = userService.update(request);
        responseService.add( new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }
    @RequestMapping("/Delete")
    public ResponseObject<User, UserDeleteRequest> delete(@RequestBody UserDeleteRequest request, HttpServletRequest httpRequest){
        ResponseObject<User, UserDeleteRequest> response = userService.delete(request);
        responseService.add( new ResponseAddRequest(JsonUtility.ResponseObject2Json(response), httpRequest));
        return response;
    }
}
