package com.unipd.semicolon.core.dao;


import com.unipd.semicolon.core.entity.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SupplierDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/webApp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123456";

    public List<Supplier> findAll() {
        final String SELECT_ALL = "SELECT id, name, address, email, telephone_number FROM supplier";

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

    public Supplier create(final Supplier supplier) {
        final String CREATE = "INSERT INTO supplier (name, address, telephone_number, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getTelephoneNumber());
            stmt.setString(4, supplier.getEmail());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                supplier.setId(rs.getLong(1));
            }
            if (stmt != null) {
                stmt.close();
            }
//            LOGGER.info("Employee %d successfully stored in the database.", employee.getBadge());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplier;
    }

    public List<Supplier> findByEmail(String email) {
        final String FIND_BY_EMAIL = "SELECT id, name, address, email, telephone_number FROM supplier WHERE email = ?";
        List<Supplier> suppliers = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            stmt = conn.prepareStatement(FIND_BY_EMAIL);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return suppliers;
    }
}
