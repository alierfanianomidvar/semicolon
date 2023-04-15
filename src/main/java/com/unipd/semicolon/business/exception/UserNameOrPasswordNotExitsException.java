package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserNameOrPasswordNotExitsException extends CustomException {

  public UserNameOrPasswordNotExitsException() {
    super("user_name_or_password_not_exists",HttpStatus.NOT_FOUND);
    this.setMsg("user_name_or_password_not_exists");
  }
}
