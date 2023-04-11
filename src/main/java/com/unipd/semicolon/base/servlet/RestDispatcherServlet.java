/*
 * Copyright 2018 University of Padua, Italy
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

package com.unipd.semicolon.base.servlet;

import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.base.resource.LogContext;
import com.unipd.semicolon.base.resource.Message;
import com.unipd.semicolon.base.rest.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Dispatches the request to the proper REST resource.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
@WebServlet(urlPatterns = "/suppliers", loadOnStartup = 1)
@ImportResource({"classpath*:context.xml"})
public final class RestDispatcherServlet extends AbstractDatabaseServlet {

	/**
	 * The JSON UTF-8 MIME media type
	 */
	private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse res) throws IOException {

		LogContext.setIPAddress(req.getRemoteAddr());

		final OutputStream out = res.getOutputStream();

		try {

			// if the requested resource was an Employee, delegate its processing and return
			if (processSupplier(req, res)) {
				return;
			}

			// if none of the above process methods succeeds, it means an unknown resource has been requested
			LOGGER.warn("Unknown resource requested: %s.", req.getRequestURI());

			final Message m = new Message("Unknown resource requested.", "E4A6",
					String.format("Requested resource is %s.", req.getRequestURI()));
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			res.setContentType(JSON_UTF_8_MEDIA_TYPE);
			m.toJSON(out);
		} catch (Throwable t) {
			LOGGER.error("Unexpected error while processing the REST resource.", t);

			final Message m = new Message("Unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(out);
		} finally {

			// ensure to always flush and close the output stream
			if (out != null) {
				out.flush();
				out.close();
			}

			LogContext.removeIPAddress();
		}
	}


	/**
	 * Checks whether the request if for an {@link Supplier} resource and, in case, processes it.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 *
	 * @return {@code true} if the request was for an {@code Employee}; {@code false} otherwise.
	 *
	 * @throws Exception if any error occurs.
	 */
	private boolean processSupplier(final HttpServletRequest req, final HttpServletResponse res) throws Exception {

		final String method = req.getMethod();

		String path = req.getRequestURI();
		Message m = null;

		// the requested resource was not an employee
		if (path.lastIndexOf("/suppliers") <= 0) {
			return false;
		}

		// strip everything until after the /supplier
		path = path.substring(path.lastIndexOf("suppliers") + 8);

		// the request URI is: /supplier
		// if method GET, list supplier
		if (path.length() == 0 || path.equals("/")) {

			switch (method) {
				case "GET":
					new ListSupplierRR(req, res, getConnection()).serve();
					break;

				default:
					LOGGER.warn("Unsupported operation for URI /suppliers: %s.", method);

					m = new Message("Unsupported operation for URI /suppliers.", "E4A5",
							String.format("Requested operation %s.", method));
					res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					m.toJSON(res.getOutputStream());
					break;
			}
		}
		/* else {
			// the request URI is: /employee/salary/{salary}
			if (path.contains("salary")) {
				path = path.substring(path.lastIndexOf("salary") + 6);

				if (path.length() == 0 || path.equals("/")) {
					LOGGER.warn("Wrong format for URI /employee/salary/{salary}: no {salary} specified. Requested URI: %s.", req.getRequestURI());

					m = new Message("Wrong format for URI /employee/salary/{salary}: no {salary} specified.", "E4A7",
							String.format("Requested URI: %s.", req.getRequestURI()));
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					m.toJSON(res.getOutputStream());
				} else {
					switch (method) {
						case "GET":
							new SearchEmployeeBySalaryRR(req, res, getConnection()).serve();

							break;
						default:
							LOGGER.warn("Unsupported operation for URI /employee/salary/{salary}: %s.", method);

							m = new Message("Unsupported operation for URI /employee/salary/{salary}.", "E4A5",
									String.format("Requested operation %s.", method));
							res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
							m.toJSON(res.getOutputStream());
							break;
					}
				}
			} else {
				// the request URI is: /employee/{badge}

				switch (method) {
					case "GET":
						new ReadEmployeeRR(req, res, getConnection()).serve();
						break;
					case "PUT":
						new UpdateEmployeeRR(req, res, getConnection()).serve();
						break;
					case "DELETE":
						new DeleteEmployeeRR(req, res, getConnection()).serve();
						break;
					default:
						LOGGER.warn("Unsupported operation for URI /employee/{badge}: %s.", method);

						m = new Message("Unsupported operation for URI /employee/{badge}.", "E4A5",
								String.format("Requested operation %s.", method));
						res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
						m.toJSON(res.getOutputStream());
				}
			}
		}*/

		return true;

	}
}
