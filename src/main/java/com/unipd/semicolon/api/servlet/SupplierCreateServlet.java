package com.unipd.semicolon.api.servlet;

import com.unipd.semicolon.api.model.Message;
import com.unipd.semicolon.business.exception.PharmacyExistsException;
import com.unipd.semicolon.business.service.Imp.SupplierServiceImp;
import com.unipd.semicolon.business.service.SupplierService;
import com.unipd.semicolon.core.entity.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/supplier", loadOnStartup = 1)
public class SupplierCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SupplierService supplierService;

    public void init() {
        supplierService = new SupplierServiceImp();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Message m = null;
        Supplier supplier = null;
        // request parameter
        String name = null;
        String address = null;
        String email = null;
        String telephoneNumber = null;
        //TODO: token is something that is passed through request header
        // user need to access it propely. the token defiend below is just for removing the errors
        // it is incorrect to use the token like this
        String token = "";


        String jsonString = IOUtils.toString(req.getReader());
        JSONObject jo = null;
        try {
            jo = new JSONObject(jsonString);
            // model
            // retrieves the request parameter
            name = String.valueOf(jo.getString("name"));
            address = String.valueOf(jo.getString("address"));
            email = String.valueOf(jo.getString("email"));
            telephoneNumber = String.valueOf(jo.getString("telephoneNumber"));
            supplier = supplierService.create(name, address, email, telephoneNumber, token);
        } catch (JSONException ex) {
//            throw new CreatePharmacyDataNotFound();
            m = new Message("Cannot create the employee data. The provided data not correct.",
                    "E100", ex.getMessage());
        } catch (PharmacyExistsException ex) {
            m = new Message(String.format("Cannot create the Supplier: another supplier with the same email already exists.\n Email: %s", email),
                    "E300", ex.getMessage());
        } catch (SQLException ex) {
            if ("23505".equals(ex.getSQLState())) {
                m = new Message(String.format("Cannot create the Supplier: another supplier with the same email already exists.\n Email: %s", email),
                        "E300", ex.getMessage());
            } else {
                m = new Message("Cannot create the Supplier: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

            }
        }

        try {
            req.setAttribute("message", m);
            req.setAttribute("supplier", supplier);
            req.getRequestDispatcher("/WEB-INF/jsp/createSupplierResult.jsp").forward(req, res);
        } catch (Exception ex) {
            throw ex;
        }

    }

}
