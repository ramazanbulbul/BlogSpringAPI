package me.ramazanbulbul.blog.dto.dev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ResponseObject<T, S> {
    @Getter
    @Setter
    int responseCode;

    @Getter
    @Setter
    String responseMessage;
    boolean isSuccess;
    @Getter
    Date responseTimestamp;
    @Getter
    @Setter
    T response;
    S request;
    public ResponseObject(S request){
        this.request = request;
        this.responseCode = 1;
        this.responseMessage = "İşlem Başarılı";
        this.responseTimestamp = new Date();
    }
    public ResponseObject(int responseCode, T response){
        this.response = response;
        responseTimestamp = new Date();
        this.responseCode = responseCode;
        this.isSuccess = this.responseCode > 0;
    }

    public boolean getIsSuccess(){
        return this.responseCode > 0;
    }
}
