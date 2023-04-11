/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.unipd.semicolon.base.database;

import com.unipd.semicolon.core.entity.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lists all the employees in the database.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class ListSupplierDAO extends AbstractDAO<List<Supplier>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT badge, surname, age, salary FROM Ferro.Employee";

	/**
	 * Creates a new object for listing all the employees.
	 *
	 * @param con the connection to the database.
	 */
	public ListSupplierDAO(final Connection con) {
		super(con);
	}

	@Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Supplier> suppliers = new ArrayList<Supplier>();

		try {
			pstmt = con.prepareStatement(STATEMENT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				suppliers.add(new Supplier(
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("email"),
						rs.getString("telephoneNumber"),
						rs.getString("previousOrders"),
						rs.getString("arrivingOrders")
				));
			}

			LOGGER.info("Employee(s) successfully listed.");
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		outputParam = suppliers;
	}
}
