package me.ramazanbulbul.blog.services;

import me.ramazanbulbul.blog.dto.response.ResponseAddRequest;
import me.ramazanbulbul.blog.entities.Response;
import me.ramazanbulbul.blog.repos.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ResponseService extends BaseService{
    @Autowired
    public ResponseService(ResponseRepository responseRepository){
        this.responseRepository = responseRepository;
    }
    public Response add(ResponseAddRequest request){
        Response response = new Response();


        response.setResponseDate(new Date());
        response.setResponseJson(request.getResponseJson());

        response.setRequestId(request.getRequestId());
        response.setRequestURI(request.getRequestURI());
        response.setRequestedSessionId(request.getRequestedSessionId());
        response.setRequestURL(request.getRequestURL());

        response.setLocalAddr(request.getLocalAddr());
        response.setLocalPort(request.getLocalPort());
        response.setLocalName(request.getLocalName());

        response.setRemoteAddr(request.getRemoteAddr());
        response.setRemotePort(request.getRemotePort());
        response.setRemoteHost(request.getRemoteHost());

        response.setServletPath(request.getServletPath());
        response.setServerPort(request.getServerPort());
        response.setServerName(request.getServerName());

        response.setAuthType(request.getAuthType());
        response.setMethod(request.getMethod());

        return responseRepository.save(response);
    }
}
