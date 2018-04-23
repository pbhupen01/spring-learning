package practice.spring.jpabasics.dto;

import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorResponse {

    private HttpStatus status;
    private String timestamp;
    private String message;
    private String debugMessage;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        this.timestamp = formatter.format(new Date());
    }

    public ErrorResponse(HttpStatus status, String message, Exception ex) {
        this(status, message);
        //this.debugMessage = ExceptionUtils.getStackTrace(ex);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public String toString()
    {
        return String.format("HttpStatus: %s. Error Message: %s", status.toString(), message);
    }
}
