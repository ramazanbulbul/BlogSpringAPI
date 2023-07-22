package me.ramazanbulbul.blog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import me.ramazanbulbul.blog.dto.dev.ResponseObject;

public class JsonUtility {
    public static String ResponseObject2Json(ResponseObject responseObject){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(responseObject);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }
}
