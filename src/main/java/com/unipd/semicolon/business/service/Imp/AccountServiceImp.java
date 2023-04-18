package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.mapper.AccountMapper;
import com.unipd.semicolon.business.service.LocalTimeService;
import com.unipd.semicolon.business.service.AccountService;
import com.unipd.semicolon.business.service.SecurityService;
import com.unipd.semicolon.business.service.StringService;
import com.unipd.semicolon.core.domain.AccountResponse;
import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.repository.entity.LoginRepository;
import com.unipd.semicolon.core.repository.entity.UserRepository;
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

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public AccountResponse Login(
            String username,
            String password) {
        Login login = loginRepository.findByUsername(username);
        if (password.equals(stringService.decodeBase64(login.getPassword()))) {
            login.setToken(securityService.createToken(
                    login.getId().toString(),
                    login.getUser().getRole().getRole()));
            login.setLastLoginDate(localTimeService.getLocalDateTime());
        }
        Login save = loginRepository.save(login);
        return AccountMapper.loginMapper(save);
    }

    @Override
    public Boolean LogOut(String token) {
        Long id = Long.parseLong(securityService.getAccountId(token));
        Login login = loginRepository.findById(id).get();
        if (login != null) {
            login.setToken(null);
            loginRepository.save(login);
            return true;
        } else {
            return false;
        }
    }

    // only inside of project must be call and no api have right to call this.
    @Override
    public Login save(
            String username,
            String password,
            User userId) {

        String passwordEncoder = stringService.encodeBase64(password);
        User user = userRepository.findUserById(userId.getId());

        Login login = new Login(
                username,
                passwordEncoder,
                null,
                null,
                localTimeService.getLocalDateTime(),
                null,
                user
        );

        return loginRepository.save(login);
    }
}
