package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.exception.UserExsitsException;
import com.unipd.semicolon.business.mapper.UserMapper;
import com.unipd.semicolon.business.service.UserService;

import com.unipd.semicolon.core.domain.UserResponse;
import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.RoleRepository;
import com.unipd.semicolon.core.repository.entity.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private LoginService loginService;


    @Override
    public Boolean save(String username,
                        String password,
                        String name,
                        String lastName,
                        Gender gender,
                        LocalDateTime birthDate,
                        String phoneNumber,
                        String address,
                        Role role,
                        String email,
                        Boolean accountStatus,
                        byte[] profilePicture
    ) {

//        if (loginService.exists(username)) {
//            throw new UserExsitsException();
//        } else {
//            Login login = loginService.creat(username, password);
//
//            User user = new User(name,
//                    lastName,
//                    gender,
//                    birthDate,
//                    phoneNumber,
//                    address,
//                    role,
//                    email,
//                    accountStatus,
//                    profilePicture,
//                    null);
//
//            userRepository.save(user);
//        }
        return false;
    }

    @Override
    public Boolean edit(Long userId,
                        String name,
                        String lastName,
                        Gender gender,
                        LocalDateTime birthDate,
                        String phoneNumber,
                        String address,
                        Role role,
                        String email,
                        Boolean accountStatus,
                        byte[] profilePicture
    ) {
        if (userId != null) {
            // Retrieve the user from the database
            if (userRepository.findUserById(userId) != null) {
                User user = userRepository.findUserById(userId);

                // Validate inputs
                if (name != null && !name.isBlank()) {
                    user.setName(name);
                }

                if (lastName != null && !lastName.isBlank()) {
                    user.setLastName(lastName);
                }

                if (gender != null) {
                    user.setGender(gender);
                }

                if (birthDate != null) {
                    user.setBirthDate(birthDate);
                }

                if (phoneNumber != null) {
                    user.setPhoneNumber(phoneNumber);
                }

                if (address != null && !address.isBlank()) {
                    user.setAddress(address);
                }

                if (role != null) {
                    user.setRole(role);
                }

                if (email != null && !email.isBlank()) {
                    user.setEmail(email);
                }

                if (accountStatus != null) {
                    user.setAccountStatus(accountStatus);
                }

                if (profilePicture != null) {
                    user.setProfilePicture(profilePicture);
                }


                // Save changes to the database
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean changeStatus(Long Id) {
        return null;
    }

    //
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

    @Override
    public UserResponse getById(long id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            return new UserResponse(
                    user.getName(),
                    user.getLastName(),
                    user.getGender(),
                    user.getBirthDate(),
                    user.getPhoneNumber(),
                    user.getAddress(),
                    user.getRole(),
                    user.getEmail()
            );
        }
        throw new EntityNotFoundException("User not found with id:" + id);
    }

    @Override
    public boolean delete(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Cannot delete null user!");
        } else {
            try {
                User user = userRepository.findUserById(id);
                userRepository.delete(user);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
