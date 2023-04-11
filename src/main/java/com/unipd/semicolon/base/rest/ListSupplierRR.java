/*
 * Copyright 2023 University of Padua, Italy
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

package com.unipd.semicolon.base.rest;

import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.base.resource.Message;
import com.unipd.semicolon.base.database.ListSupplierDAO;
import com.unipd.semicolon.base.resource.Actions;
import com.unipd.semicolon.base.resource.ResourceList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * A REST resource for listing {@link Supplier}s.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class ListSupplierRR extends AbstractRR {

	/**
	 * Creates a new REST resource for listing {@code Employee}s.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 * @param con the connection to the database.
	 */
	public ListSupplierRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
		super(Actions.LIST_EMPLOYEE, req, res, con);
	}


	@Override
	protected void doServe() throws IOException {

		List<Supplier> el = null;
		Message m = null;

		try {

			// creates a new DAO for accessing the database and lists the employee(s)
			el = new ListSupplierDAO(con).access().getOutputParam();

			if (el != null) {
				LOGGER.info("Employee(s) successfully listed.");

				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList(el).toJSON(res.getOutputStream());
			} else { // it should not happen
				LOGGER.error("Fatal error while listing employee(s).");

				m = new Message("Cannot list employee(s): unexpected error.", "E5A1", null);
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				m.toJSON(res.getOutputStream());
			}
		} catch (SQLException ex) {
			LOGGER.error("Cannot list employee(s): unexpected database error.", ex);

			m = new Message("Cannot list employee(s): unexpected database error.", "E5A1", ex.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
	}


}
