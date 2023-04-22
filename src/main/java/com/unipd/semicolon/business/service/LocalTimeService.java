package com.unipd.semicolon.business.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface LocalTimeService {

    LocalDateTime getLocalDateTime();

    LocalDate getLocalDate();

    Long nowTime();
}
