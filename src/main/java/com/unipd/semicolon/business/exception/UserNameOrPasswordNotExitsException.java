package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class UserNameOrPasswordNotExitsException extends CustomException {

  public UserNameOrPasswordNotExitsException() {
    super("user_name_or_password_not_exists", HttpStatus.NOT_FOUND);
  }
}
