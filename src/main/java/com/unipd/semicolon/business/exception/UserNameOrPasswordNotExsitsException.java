package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class UserNameOrPasswordNotExsitsException extends CustomException {

  public UserNameOrPasswordNotExsitsException() {
    super("user_name_or_password_not_exists", HttpStatus.NOT_FOUND);
  }
}
