package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CreatePharmacyDataNotFound extends CustomException {

  public CreatePharmacyDataNotFound() {
    super("Create_pharmacy_data_not_found");
  }

}
