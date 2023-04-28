package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class DoseNotExistException extends CustomException {

  public DoseNotExistException() {
    super("not_found", HttpStatus.NOT_FOUND);
    this.setData("not_found");
  }
}
