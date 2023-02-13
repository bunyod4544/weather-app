package com.example.weatherapp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

/**
 * @author Doston Bokhodirov on 05 November 2022 at 5:20 PM
 */

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseError {
    private String message;
    private String path;
    private String reason;

    public ResponseError(String reason, String message) {
        this.reason = reason;
        this.message = message;
    }

    public ResponseError(String reason, String message, String path) {
        this(reason, message);
        this.path = path;
    }

    public ResponseError(String reason, String message, @Nullable WebRequest request) {
        this(reason, message);
        if (Objects.nonNull(request)) this.path = ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    public static ResponseError response(String reason, String message) {
        return new ResponseError(reason, message);
    }

    public static ResponseError response(String reason, String message, @Nullable WebRequest request) {
        return new ResponseError(reason, message, request);
    }

}
