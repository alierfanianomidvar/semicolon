package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.repository.entity.LoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImp extends CustomRepository implements LoginRepository {
    public boolean login(Login l) throws SQLException {
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
