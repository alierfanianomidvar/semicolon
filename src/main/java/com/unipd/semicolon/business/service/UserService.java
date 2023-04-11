package com.unipd.semicolon.business.service;


import com.unipd.semicolon.core.domain.UserListExampleResponse;
import com.unipd.semicolon.core.domain.UserResponse;
import com.unipd.semicolon.core.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface UserService {

    User save(
            String username,
            String password,
            String name,
            String familyName,
            Date birthday,
            Short role
    );

    Boolean edit(
            Long id,
            String name,
            String familyName,
            Date birthday,
            Short role
    );

    List<UserResponse> getAll();

    List<UserListExampleResponse> findAllByFamilyName(String familyName);
}
