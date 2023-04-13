package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.exception.UserExsitsException;
import com.unipd.semicolon.business.mapper.UserMapper;
import com.unipd.semicolon.core.domain.UserListExampleResponse;
import com.unipd.semicolon.core.domain.UserResponse;
import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.RoleRepository;
import com.unipd.semicolon.core.repository.entity.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LoginService loginService;


    //TODO: change the types phonenumber, accountStatus and ....
    @Override
    public User save(
            String username,
            String password,
            String name,
            String lastName,
            Gender gender,
            LocalDateTime birthDate,
            String phoneNumber,
            String address,
            Role role,
            String email,
            boolean accountStatus,
            byte[] profilePicture
            ) {

        if (loginService.exists(username)) {
            throw new UserExsitsException();
        } else {
            Login login = loginService.creat(username, password);

            User user = new User(name,
                    lastName,
                    gender,
                    birthDate,
                    phoneNumber,
                    address,
                    role,
                    email,
                    accountStatus,
                    profilePicture);

            userRepository.save(user);
        }
        return null;
    }

    @Override
    public Boolean edit(
            Long id,
            String name,
            String familyName,
            Date birthday,
            Short role) {

        if (userRepository.findUserById(id) != null) {
            User user = userRepository.findUserById(id);
            if (name != null) {
                user.setName(name);
            }
            if (familyName != null) {
                user.setFamilyName(familyName);
            }
            if (birthday != null) {
                user.setBirthday(birthday);
            }
            if (role != null) {
                user.setName(name);
            }
            userRepository.save(user); // to save the changes on the database.

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> userList = new ArrayList<>();
        try {
            List<User> users = userRepository.getAll();
            if (users == null || users.isEmpty()) {
                throw new NotFoundException();
            }
            for (User user : users) {
                userList.add(UserMapper.userResponse(user));
            }
        } catch (DataAccessException ex) {
            throw new ServiceException("Failed to retrieve users.", ex);
        }
        return userList;
    }


}
