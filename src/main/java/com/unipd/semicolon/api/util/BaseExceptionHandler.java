package com.unipd.semicolon.api.util;

import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.exception.InvalidTokenException;
import com.unipd.semicolon.business.exception.UserExistsException;
import com.unipd.semicolon.business.exception.UserNameOrPasswordNotExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return ResponseHelper.response(
                    "ex.getCause()",
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
        return ResponseHelper.response(
                ex.getClass(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UserNameOrPasswordNotExistsException.class)
    public ResponseEntity<Object> UserNameOrPasswordNotExitsException(
            UserNameOrPasswordNotExistsException e) {
        return ResponseHelper.response(
                "Username: " + e.getUsername() + "  " + "Password : " + e.getPassword(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> InvalidTokenException(
            InvalidTokenException e) {
        return ResponseHelper.response(
                e.getToken(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> UserExistsException(
            UserExistsException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }


}
