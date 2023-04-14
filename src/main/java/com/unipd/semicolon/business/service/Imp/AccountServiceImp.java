package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.UserNameOrPasswordNotExitsException;
import com.unipd.semicolon.business.service.LocalTimeService;
import com.unipd.semicolon.business.service.AccountService;
import com.unipd.semicolon.business.service.StringService;
import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.repository.entity.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private StringService stringService;

    @Autowired
    private LocalTimeService localTimeService;

    @Override
    public String Login(
            String username,
            String password) {

        Login login = loginRepository.findByUsername(username);
        if (stringService.verifyBCryptPassword(password, login.getPassword())) {
            login.setSession(true);
            login.setLastLoginDate(localTimeService.getLocalDateTime());
            loginRepository.save(login);
        } else {
            throw new UserNameOrPasswordNotExitsException();
        }

        return null;

    }

    @Override
    public void logOut(Long id) {

    }
}
