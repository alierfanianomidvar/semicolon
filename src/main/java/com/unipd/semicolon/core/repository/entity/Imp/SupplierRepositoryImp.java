package com.unipd.semicolon.core.repository.entity.Imp;


import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.repository.entity.SupplierRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SupplierRepositoryImp extends CustomRepository implements SupplierRepository {
    public boolean editSupplier(Supplier s) throws SQLException {
        return false;
    }

    public boolean addSupplier(Supplier s) throws SQLException {

        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet addResult = null;

        return false;

    }


    //TO DO
    //check exception

    public boolean removingSuppliers(Supplier s) throws SQLException {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet removeResult = null;

        try {
            //conn = DataSourceProvider.getDataSource().getConnection();

            String query = "DELETE FROM supplier WHERE id=?;";

            stmnt = conn.prepareStatement(query);
            stmnt.setLong(1, s.getId());
            stmnt.executeUpdate();

            if (removeResult != null) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (removeResult != null) {
                removeResult.close();
            }
            if (stmnt != null) {
                stmnt.close();
            }
        }
    }

    public Supplier findById(Long id) {
        return findById(Supplier.class,id);
    }

}
