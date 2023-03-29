package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class UserExsitsException extends CustomException {

  public UserExsitsException() {
    super("user_do_not_exist", HttpStatus.METHOD_NOT_ALLOWED);
  }
}
