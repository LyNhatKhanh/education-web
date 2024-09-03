package com.lynhatkhanh.educationweb.educationweb.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "Uncategoried!", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_EXISTED(1001, "User is not Existed!", HttpStatus.BAD_REQUEST),


    UNAUTHENTICATED(2001, "Unauthenticated!", HttpStatus.UNAUTHORIZED),

    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
