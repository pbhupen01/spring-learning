package practice.spring.springrest.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerUtils {

    public final static String SUBJECTS = "subjects";
    public final static String VERSION = "v1/";

    public static <T> ResponseEntity<T> createResponseEntity(T object, HttpStatus status)
    {
        return new ResponseEntity(object, status);
    }

}
