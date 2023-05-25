package com.unipd.semicolon.core.dao;


import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.repository.entity.Imp.CustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public final class SupplierDao extends AbstractDAO<List<Supplier>>{
    private static final String URL = "jdbc:postgresql://localhost:5432/webApp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123456";

    public SupplierDao(Connection con) {
        super(con);
    }

    /*public List<Supplier> findAll(){
        final String SELECT_ALL = "select g from Supplier g order by g.id desc ";

        List<Supplier> suppliers = listQueryWrapper(entityManager.createQuery(
                SELECT_ALL,
                Supplier.class));

        return suppliers;
    }

    private <T> List<T> listQueryWrapper(TypedQuery<T> typedQuery) {
        List<T> resultList = typedQuery.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList;
    }*/

    public Supplier create(final Supplier supplier) throws SQLException {
        final String CREATE = "INSERT INTO supplier (name, address, telephone_number, email) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            stmt = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getTelephoneNumber());
            stmt.setString(4, supplier.getEmail());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                supplier.setId(rs.getLong(1));
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return supplier;
    }

 /*   public List<Supplier> findByEmail(String email) throws SQLException {
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
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }
        }

        return suppliers;
    }*/

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final String SELECT_ALL = "SELECT id, name, address, email, telephone_number FROM supplier";


        // the results of the search
        final List<Supplier> supplierArrayList = new ArrayList<Supplier>();

        try {
            pstmt = con.prepareStatement(SELECT_ALL);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setName(rs.getString("name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setEmail(rs.getString("email"));
                supplier.setTelephoneNumber(rs.getString("telephone_number"));
                supplierArrayList.add(supplier);
            }

            LOGGER.info("supplier(s) %d successfully listed.", 1);
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        this.outputParam = supplierArrayList;
    }
}
