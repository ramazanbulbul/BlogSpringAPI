package me.ramazanbulbul.blog.services;

import me.ramazanbulbul.blog.dto.dev.ResponseObject;
import me.ramazanbulbul.blog.dto.user.*;
import me.ramazanbulbul.blog.entities.User;
import me.ramazanbulbul.blog.repos.UserRepository;
import me.ramazanbulbul.blog.utils.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserService extends BaseService{
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public  ResponseObject<List<User>, UserGetAllRequest> getAll(UserGetAllRequest request){
        ResponseObject<List<User>, UserGetAllRequest> responseObject = new ResponseObject<List<User>, UserGetAllRequest>(request);
        try {
            List<User> users = userRepository.getAllByIsDeleted(false);
            responseObject.setResponse(users);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
    public ResponseObject<List<User>, UserGetAllRequest> getAllWithDeleted(UserGetAllRequest request){
        ResponseObject<List<User>, UserGetAllRequest> responseObject = new ResponseObject<List<User>, UserGetAllRequest>(request);
        try {
            List<User> users = userRepository.findAll();
            responseObject.setResponse(users);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
    public ResponseObject<User, UserGetByIdRequest> getById(UserGetByIdRequest request){
        ResponseObject<User, UserGetByIdRequest> responseObject = new ResponseObject<User, UserGetByIdRequest>(request);
        try {
            Long userId = request.getUserId();
            User user = userRepository.getByUserIdAndIsDeleted(userId, false);
            responseObject.setResponse(user);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }

    public ResponseObject<User, UserGetByUsernameRequest> getByUsername(UserGetByUsernameRequest request){
        ResponseObject<User, UserGetByUsernameRequest> responseObject = new ResponseObject<User, UserGetByUsernameRequest>(request);
        try {
            String username = request.getUsername();
            User user = userRepository.getByUsernameAndIsDeleted(username, false);
            responseObject.setResponse(user);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
    public ResponseObject<User, UserGetByEmailRequest> getByEmail(UserGetByEmailRequest request){
        ResponseObject<User, UserGetByEmailRequest> responseObject = new ResponseObject<User, UserGetByEmailRequest>(request);
        try {
            String username = request.getEmail();
            User user = userRepository.getByEmailAndIsDeleted(username, false);
            responseObject.setResponse(user);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }

    public ResponseObject<User, UserGetByIdRequest> getByIdWithDeleted(UserGetByIdRequest request){
        ResponseObject<User, UserGetByIdRequest> responseObject = new ResponseObject<User, UserGetByIdRequest>(request);
        try {
            Long userId = request.getUserId();
            User user = userRepository.getByUserId(userId);
            responseObject.setResponse(user);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
    public ResponseObject<User, UserAddRequest> add(UserAddRequest request){
        ResponseObject<User, UserAddRequest> responseObject = new ResponseObject<User, UserAddRequest>(request);
        try {
            User user = request.getUser();
            User createdUser = request.getCreatedUser();

            if (this.getById(new UserGetByIdRequest(createdUser.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Oluşturan kullanıcı bulunamadı.");
                return responseObject;
            }
            if (this.getByUsername(new UserGetByUsernameRequest(user.getUsername())).getResponse() != null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Benzer kullanıcı adı kullanan başka bir kullanıcı bulunamaktadır. Lütfen başka bir kullanıcı adı seçiniz.");
                return responseObject;
            }
            if (this.getByEmail(new UserGetByEmailRequest(user.getEmail())).getResponse() != null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Benzer eposta kullanan başka bir kullanıcı bulunamaktadır. Lütfen başka bir eposta giriniz.");
                return responseObject;
            }
            user.setPassword(HashUtility.text2MD5(user.getPassword()));
            user.setCreatedUserId(createdUser.getUserId());
            user.setCreatedTimestamp(new Date());
            User responseUser = userRepository.save(user);
            responseObject.setResponse(responseUser);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getLocalizedMessage());
        }
        return responseObject;
    }
    public ResponseObject<User, UserUpdateRequest> update(UserUpdateRequest request){
        ResponseObject<User, UserUpdateRequest> responseObject = new ResponseObject<User, UserUpdateRequest>(request);
        try {
            User user = request.getUser();
            User newUser = request.getNewUser();
            User updatedUser = request.getUpdatedUser();
            if (getById(new UserGetByIdRequest(user.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Güncellenecek kullanıcı bulunamadı.");
                return responseObject;
            }
            if (getById(new UserGetByIdRequest(newUser.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Yeni kullanıcı bulunamadı.");
                return responseObject;
            }
            if (getById(new UserGetByIdRequest(updatedUser.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Güncelleyen kullanıcı bulunamadı.");
                return responseObject;
            }
            if (user.getUserId().equals(newUser.getUserId())){
                user = newUser;

                user.setUpdatedUserId(updatedUser.getUserId());
                User responseUser = userRepository.save(user);

                responseObject.setResponse(responseUser);
            }else {
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Kullanıcı idleri eşleşmemektedir.");
            }
            return responseObject;
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }

    public ResponseObject<User, UserDeleteRequest> delete(UserDeleteRequest request){
        ResponseObject<User, UserDeleteRequest> responseObject = new ResponseObject<User, UserDeleteRequest>(request);
        try {
            User user = request.getUser();
            User deletedUser = request.getDeletedUser();

            if (this.getById(new UserGetByIdRequest(user.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Silinmek istenen kullanıcı bulunamadı.");
                return responseObject;
            }

            if (this.getById(new UserGetByIdRequest(deletedUser.getUserId())).getResponse() == null){
                responseObject.setResponseCode(-1);
                responseObject.setResponseMessage("Silmeye çalışan kullanıcı bulunamadı.");
                return responseObject;
            }

            user.setDeletedUserId(deletedUser.getUserId());
            user.setDeleted(true);
            user.setDeletedTimestamp(new Date());
            userRepository.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
            responseObject.setResponseCode(-1);
            responseObject.setResponseMessage("Hata Mesajı:" + ex.getMessage());
        }
        return responseObject;
    }
}
