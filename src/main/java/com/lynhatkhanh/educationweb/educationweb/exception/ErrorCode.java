package com.lynhatkhanh.educationweb.educationweb.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "Uncategorized error!", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_EXISTED(1001, "User is not Existed!", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTED(1001, "Permission is not Existed!", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(1001, "Role is not Existed!", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User is Existed!", HttpStatus.BAD_REQUEST),
    PERMISSION_EXISTED(1002, "Permission is Existed!", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1002, "Role is Existed!", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(2001, "Unauthenticated!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(2002, "You do not have permission!", HttpStatus.FORBIDDEN),
    TOKEN_EXPIRED(2003, "Token has already expired!", HttpStatus.UNAUTHORIZED),

    INVALID_KEY(3001, "Invalid message key!", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(3002, "Username must be at least {min} characters!", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(3003, "Password must be at least {min} characters!", HttpStatus.BAD_REQUEST),
    INVALID_DOB(3004, "Yours age must be at least {min}!", HttpStatus.BAD_REQUEST),
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
