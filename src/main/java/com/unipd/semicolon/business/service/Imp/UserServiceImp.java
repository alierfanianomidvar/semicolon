package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.exception.UserExsitsException;
import com.unipd.semicolon.business.mapper.UserMapper;
import com.unipd.semicolon.business.service.AccountService;
import com.unipd.semicolon.business.service.SecurityService;
import com.unipd.semicolon.business.service.UserService;

import com.unipd.semicolon.business.service.ValidationService;
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

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ValidationService validationService;


    @Override
    public Login save(String username,
                      String password,
                      String name,
                      String lastName,
                      Gender gender,
                      LocalDateTime birthDate,
                      String phoneNumber,
                      String address,
                      Role role,
                      String email,
                      String accountStatus,
                      byte[] profilePicture,
                      String token
    ) {

        try {
            String roleFromToken = securityService.getRoleFromToken(token);
            if(roleFromToken.contains("admin")) {
                if (accountService.findByUserName(username) != null) {
                    throw new UserExsitsException();
                } else {

                    if (validationService.validateEmail(email)
                            && validationService.validateTelephoneNumber(phoneNumber) &&
                            validationService.validateBirthDate(birthDate) && validationService.validateImage(profilePicture, 2048) &&
                            validationService.validateGender(gender)

                    ) {
                        User user = new User(name,
                                lastName,
                                gender,
                                birthDate,
                                phoneNumber,
                                address,
                                role,
                                email,
                                accountStatus,
                                profilePicture,
                                null);

                        User save = userRepository.save(user);
                        Login login = accountService.save(
                                username == null ? email : username,
                                password == null ? generatePassword(8) : password,
                                save);

                        return login;
                    } else {
                        return null;
                    }

                }
            } else {
                throw new CustomException("You are not authorized!");
            }
        } catch (CustomException e) {
            return null;
        }
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
                        String accountStatus,
                        byte[] profilePicture,
                        String token
    ) {
        try {
            String roleFromToken = securityService.getRoleFromToken(token);
            if(roleFromToken.contains("admin")) {
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

                        if (validationService.validateGender(gender)) {
                            user.setGender(gender);
                        }

                        if (validationService.validateBirthDate(birthDate)) {
                            user.setBirthDate(birthDate);
                        }

                        if (validationService.validateTelephoneNumber(phoneNumber)) {
                            user.setPhoneNumber(phoneNumber);
                        }

                        if (address != null && !address.isBlank()) {
                            user.setAddress(address);
                        }

                        if (role != null) {
                            user.setRole(role);
                        }

                        if (validationService.validateEmail(email)) {
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
                    } else {
                        throw new CustomException("No such user exists in the database!");
                    }
                } else {
                    throw new CustomException("No user id is passed!");
                }

            } else {
                throw new CustomException("You are not authorized!");
            }
        } catch (CustomException e) {
            return false;
        }
    }

    @Override
    public Boolean changeStatus(Long id, String newStatus, String token) {
        try {
            String roleFromToken = securityService.getRoleFromToken(token);
            if (roleFromToken.contains("admin")) {
                if (userRepository.findUserById(id) != null) {
                    User user = userRepository.findUserById(id);
                    user.setAccountStatus(newStatus);
                    userRepository.save(user);
                    return true;
                } else {
                    throw new CustomException("User with the provided id does not exist in the database!");
                }
            } else {
                throw new CustomException("You are not authorized!");
            }
        } catch (CustomException e) {
            return false;
        }

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
    public UserResponse getById(Long id) {
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
    public boolean delete(Long id, String token) {
        try {
            String roleFromToken = securityService.getRoleFromToken(token);
            if(roleFromToken.contains("admin")) {
                if (id < 0) {
                    throw new IllegalArgumentException("Cannot delete null user!");
                } else {
                    User user = userRepository.findUserById(id);
                    if (user != null) userRepository.delete(user);
                    return true;
                }
            } else {
                throw new CustomException("You are not Authorized!");
            }
        } catch (CustomException e) {
            return false;
        }

    }

    private String generatePassword(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        return random.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
