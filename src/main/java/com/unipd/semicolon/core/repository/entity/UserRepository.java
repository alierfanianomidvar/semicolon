package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import io.micrometer.common.lang.NonNull;


import java.util.List;


public interface UserRepository {

    User save(User user);

    User findUserById(Long id);

    List<User> getAll();

    List<User> findAllByLastName(String lastName);

    List<User> findByRoles(@NonNull Role role);
}
