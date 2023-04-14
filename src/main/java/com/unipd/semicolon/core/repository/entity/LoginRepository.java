package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Login;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.juli.logging.Log;

import java.sql.SQLException;

public interface LoginRepository {
    boolean login(Login l) throws SQLException;
    void logout(HttpServletRequest request);

    Login save(Login login);
    Login findByUsername(String username);
}
