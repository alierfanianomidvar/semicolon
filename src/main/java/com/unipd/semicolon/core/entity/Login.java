package com.unipd.semicolon.core.entity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class Login {
    private String userId;
    private String userName;
    private String password;
    private boolean session;
    private LocalDateTime lastLoginDate;
    private LocalDateTime activationDate;
    private LocalDateTime terminationDate;


    //to get the list of login information
    public Login(String userId, String userName, String password, boolean session, LocalDateTime lastLoginDate, LocalDateTime activationDate, LocalDateTime terminationDate) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.session = session;
        this.lastLoginDate = lastLoginDate;
        this.activationDate = activationDate;
        this.terminationDate = terminationDate;
    }

    // to use in login
    public Login(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDateTime getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDateTime terminationDate) {
        this.terminationDate = terminationDate;
    }

    public boolean login(Login l) throws Exception {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet result = null;
        try {
            // conn = DataSourceProvider.getDataSource().getConnection();
            String query = "SELECT * FROM users WHERE userId=? AND userName=? AND password=md5(?);";

            //prepare the statement
            stmnt = conn.prepareStatement(query);
            stmnt.setString(1, l.getUserId());
            stmnt.setString(2, l.getUserName());
            stmnt.setString(3, l.getPassword());

            //execute the query
            result = stmnt.executeQuery();

            //handle the results of the query
            if (result.next()) {
                return true;
                //LOGGER.info("Student logged in {}.", student_new.getUserId());
            }
            return false;
            //else {LOGGER.error("error logging in the student {}",student.getUserId());}

        } finally {
            //closing all the possible resources
            if (result != null) {
                result.close();
            }
            if (stmnt != null) {
                stmnt.close();
            }
        }
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}