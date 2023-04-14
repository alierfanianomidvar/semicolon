package com.unipd.semicolon.core.dao;



import com.unipd.semicolon.core.entity.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/webApp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123456";

    private static final String SELECT_ALL = "SELECT id, name, address, email, telephone_number   FROM supplier";

    public List<Supplier> findAll() {
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setName(rs.getString("name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setEmail(rs.getString("email"));
                supplier.setTelephoneNumber(rs.getString("telephone_number"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }
}
