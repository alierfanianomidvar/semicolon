package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.LocalTimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class LocalTimeServiceImp implements LocalTimeService {

    @Override
    public  LocalDateTime getLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //correct format of time.
        String formattedDateTime = now.format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }

    @Override
    public LocalDate getLocalDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = currentDate.format(formatter);
        return LocalDate.parse(formattedDate, formatter);
    }

    @Override
    public Long nowTime() {
        Date now = new Date();
        return now.getTime();
    }
}
