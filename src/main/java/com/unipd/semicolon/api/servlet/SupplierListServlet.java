package com.unipd.semicolon.api.servlet;

import com.unipd.semicolon.business.service.Imp.SupplierServiceImp;
import com.unipd.semicolon.business.service.SupplierService;
import com.unipd.semicolon.core.entity.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet(urlPatterns = "/supplier/list", loadOnStartup = 1)
public class SupplierListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SupplierService supplierService;

    public void init() {
        supplierService = new SupplierServiceImp();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Supplier> suppliers = supplierService.getSupplierList();
        request.setAttribute("suppliers", suppliers);
        request.getRequestDispatcher("/WEB-INF/jsp/SupplierList.jsp").forward(request, response);
    }
}
