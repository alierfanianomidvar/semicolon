package com.unipd.semicolon.core.repository.entity.Imp;


import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.repository.entity.SupplierRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRepositoryImp extends CustomRepository implements SupplierRepository {
    public boolean editSupplier(Supplier s) throws SQLException {
        PreparedStatement stmnt = null;
        Connection conn= null;
        ResultSet updateResult = null;
        try {
            //conn = DataSourceProvider.getDataSource().getConnection();

            String query = "UPDATE supplier SET id=?, name=?, address=?, email=?, telephoneNumber=?, " +
                    "previousOrder=?, arrivingOrders=?, WHERE id=?;";

            stmnt = conn.prepareStatement(query);
            stmnt.setLong(1, s.getId());
            stmnt.setString(2, s.getName());
            stmnt.setString(3, s.getAddress());
            stmnt.setString(4, s.getEmail());
            stmnt.setString(5, s.getTelephoneNumber());
            stmnt.setString(6, s.getPreviousOrders());
            stmnt.setString(7, s.getArrivingOrders());
            stmnt.executeUpdate();

            if (updateResult.next()) {
                return true;
            }
            return false;

        } finally {
            //cleaningOperations(stmnt, conn);
            if (updateResult != null) {
                updateResult.close();
            }
            if (stmnt != null) {
                stmnt.close();
            }
        }
    }

    public boolean addSupplier(Supplier s) throws SQLException {

        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet addResult = null;

        try {
            // conn = DataSourceProvider.getDataSource().getConnection();
            String query = "INSERT INTO supplier VALUES (?, ?, ?, ?, ?, ?, ?);";

            stmnt = conn.prepareStatement(query);
            stmnt.setLong(1, s.getId());
            stmnt.setString(2, s.getName());
            stmnt.setString(3, s.getAddress());
            stmnt.setString(4, s.getEmail());
            stmnt.setString(5, s.getTelephoneNumber());
            stmnt.setString(6, s.getPreviousOrders());
            stmnt.setString(7, s.getArrivingOrders());
            stmnt.executeUpdate();

            if (addResult != null) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (addResult != null) {
                addResult.close();
            }
            if (stmnt != null) {
                stmnt.close();
            }
        }
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

}
