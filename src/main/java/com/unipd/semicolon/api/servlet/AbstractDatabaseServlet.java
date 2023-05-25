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

package com.unipd.semicolon.api.servlet;

import com.unipd.semicolon.AppConfig;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDatabaseServlet extends HttpServlet {


		protected static final Logger LOGGER = LogManager.getLogger(AbstractDatabaseServlet.class,
				StringFormatterMessageFactory.INSTANCE);


		private DataSource ds;


		/*public void init(ServletConfig config) throws ServletException {

			// the JNDI lookup context
			InitialContext cxt;

			try {
				cxt = new InitialContext();
				ds = (DataSource) cxt.lookup("jdbc:postgresql://localhost:5432/webApp");

				ds.getConnection("postgres", "123456");

				LOGGER.info("Connection pool to the database pool successfully acquired.");
			} catch (NamingException e) {
				ds = null;

				LOGGER.error("Unable to acquire the connection pool to the database.", e);

				throw new ServletException("Unable to acquire the connection pool to the database", e);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}*/

		public void init(ServletConfig config) throws ServletException {
			ds = new AppConfig().dataSource();
			LOGGER.info("Connection pool to the database pool successfully acquired.");
			/*try {
				ds.getConnection("postgres", "123456");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}*/
		}

		public void destroy() {
			ds = null;
			LOGGER.info("Connection pool to the database pool successfully released.");
		}

		protected final Connection getConnection() throws SQLException {
			ds = new AppConfig().dataSource();
			try {
				return ds.getConnection();
			} catch (final SQLException e) {
				LOGGER.error("Unable to acquire the connection from the pool.", e);
				throw e;
			}
		}

	}
