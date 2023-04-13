package com.unipd.semicolon.business.service;


import com.unipd.semicolon.core.domain.UserListExampleResponse;
import com.unipd.semicolon.core.domain.UserResponse;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.entity.enums.Gender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface UserService {

    //TODO: phone number is Long in Entity part => should change to String
    User save(
            String username,
            String password,
            String name,
            String lastName,
            Gender gender,
            Date birthDate,
            String phoneNumber,
            String address,
            Role role,
            String email,
            boolean accountStatus,
            byte[] profilePicture
    );

    //For password retrieval we need to
    Boolean edit(
            Long id,
            String name,
            String familyName,
            Gender gender,
            Date birthDate,
            String phoneNumber,
            String address,
            Role role,
            String email
    );

    //Id => userId
    Boolean changeStatus(
            Long Id
    );

    List<UserResponse> getAll();


    //TODO: User and Login entities must be connected
    //List<UserResponse> findByUserName(User userName);
    

    void delete(User user);
}
