package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.repository.entity.RoleRepository;
import com.unipd.semicolon.core.repository.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //@Autowired
    //private LoginService loginService;


    /*@Override
    public User save(
            String username,
            String password,
            String name,
            String familyName,
            Date birthday,
            Short roleId) {

        if (loginService.exists(username)) {
            throw new UserExsitsException();
        } else {
            Login login = loginService.creat(username, password);

            User user = new User(name,
                    familyName,
                    login,
                    birthday,
                    roleRepository.findById(roleId));

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
        // here we don't need to pass the info of login to front because
        // inside the login we have the password of the users, so we must use userResponse we can create
        // userResponse base on what info we need to pass.

        List<UserResponse> userList = new ArrayList<>();
        for (User user : userRepository.getAll()) {
            userList.add(UserMapper.userResponse(user));
        }
        return userList;
    }

    @Override
    public List<UserListExampleResponse> findAllByFamilyName(String familyName) {
        List<UserListExampleResponse> userList = new ArrayList<>();
        for (User user : userRepository.findAllByFamilyName(familyName)) {
            userList.add(UserMapper.userListExampleResponse(user));
            // we dont want to send all info of user to front, so we will use a mapper here
            // If we for admin we need all info we can create another api just for admin.
        }

        // if we don't have anybody with this family name we can do 2 things ->
        // 1. send an empty list
        // 2. we can rise an exception.

        return userList;
    }*/
}
