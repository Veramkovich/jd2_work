package it.academy.servlet;

import it.academy.data.DaoFactory;
import it.academy.data.DatabaseName;
import it.academy.data.ProductSpec;
import it.academy.data.ProductSpecDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    DaoFactory daoFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        final String databaseName = config.getServletContext().getInitParameter("database.name");
        try {
            daoFactory = DaoFactory.getInstance(DatabaseName.valueOf(databaseName));
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter writer = resp.getWriter();

            ProductSpecDao productSpecDao = daoFactory.getProductSpecDao();
            final List<ProductSpec> productSpecs = productSpecDao.readAll();
            for (ProductSpec product : productSpecs) {
                System.out.println("id=" + product.getId() +
                        " name=" + product.getProductName() +
                        " details=" + product.getProductDetails());
                writer.println("id=" + product.getId() +
                        " name=" + product.getProductName() +
                        " details=" + product.getProductDetails() +
                        " date=" + product.getProductDate());
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
