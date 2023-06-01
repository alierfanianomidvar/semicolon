package com.unipd.semicolon.core.dao;


import com.unipd.semicolon.core.entity.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public final class SupplierListDao extends AbstractDAO<List<Supplier>> {


    private Boolean flag;

    public SupplierListDao(Connection con, Boolean flag) {

        super(con);
        this.flag = flag;
    }


    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final String SELECT_ALL = "SELECT id, name, address, email, telephone_number FROM supplier";
        final String FIND_BY_EMAIL = "SELECT id, name, address, email, telephone_number FROM supplier WHERE email = ?";

        // the results of the search
        final List<Supplier> supplierArrayList = new ArrayList<Supplier>();

        try {

            if (this.flag) {
                pstmt = con.prepareStatement(SELECT_ALL);
            } else {
                pstmt = con.prepareStatement(FIND_BY_EMAIL);
            }

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