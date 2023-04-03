package com.unipd.semicolon.core.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Supplier {
    private String id;
    private String name;
    private String address;
    private String email;
    private String telephoneNumber;
    private String previousOrders;
    private String arrivingOrders;

    public Supplier(String id, String name, String address, String email, String telephoneNumber, String previousOrders, String arrivingOrders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.previousOrders = previousOrders;
        this.arrivingOrders = arrivingOrders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPreviousOrders() {
        return previousOrders;
    }

    public void setPreviousOrders(String previousOrders) {
        this.previousOrders = previousOrders;
    }

    public String getArrivingOrders() {
        return arrivingOrders;
    }

    public void setArrivingOrders(String arrivingOrders) {
        this.arrivingOrders = arrivingOrders;
    }

    public static boolean editSupplier(Supplier s) throws Exception {
        PreparedStatement stmnt = null;
        Connection conn= null;
        ResultSet updateResult = null;
        try {
            //conn = DataSourceProvider.getDataSource().getConnection();

            String query = "UPDATE supplier SET id=?, name=?, address=?, email=?, telephoneNumber=?, " +
                    "previousOrder=?, arrivingOrders=?, WHERE id=?;";

            stmnt = conn.prepareStatement(query);
            stmnt.setString(1, s.getId());
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

    public static boolean addSupplier(Supplier s) throws Exception {

        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet addResult = null;

        try {
            // conn = DataSourceProvider.getDataSource().getConnection();
            String query = "INSERT INTO suppliers VALUES (?, ?, ?, ?, ?, ?, ?);";

            stmnt = conn.prepareStatement(query);
            stmnt.setString(1, s.getId());
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


    public static boolean removingSuppliers(Supplier s) throws Exception {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet removeResult = null;

        try {
            //conn = DataSourceProvider.getDataSource().getConnection();

            String query = "DELETE FROM suppliers WHERE id=?;";

            stmnt = conn.prepareStatement(query);
            stmnt.setString(1, s.getId());
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

