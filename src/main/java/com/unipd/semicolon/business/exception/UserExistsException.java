package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class UserExistsException extends CustomException {

  public UserExistsException() {
    super("user_do_not_exist", HttpStatus.NOT_FOUND);
  }
}
