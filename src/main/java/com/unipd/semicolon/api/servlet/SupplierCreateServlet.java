package com.unipd.semicolon.api.servlet;

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

@WebServlet(urlPatterns = "/supplier", loadOnStartup = 1)
public class SupplierCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SupplierService supplierService;

    public void init() {
        supplierService = new SupplierServiceImp();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // request parameter
        String name = null;
        String address = null;
        String email = null;
        String telephoneNumber = null;


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

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Supplier supplier = supplierService.create(name, address, email, telephoneNumber);
        req.setAttribute("supplier", supplier);
        req.getRequestDispatcher("/WEB-INF/jsp/createSupplierResult.jsp").forward(req, res);

    }

}
