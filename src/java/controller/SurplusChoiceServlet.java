package controller;

import dataaccesslayer.DataSource;
import model.RetailerInventoryWorker;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This servlet is used to determine the surplus choice of a retailer
 */
@WebServlet(name = "SurplusChoiceServlet", urlPatterns = {"/SurplusChoiceServlet"})
public class SurplusChoiceServlet extends HttpServlet {
    DataSource dataSource;
    RetailerInventoryWorker worker = new RetailerInventoryWorker();

    /**
     * Initializes the servlet. used throughout most servlets to instantiate the datasource
     * @throws ServletException if there is a servlet error
     */
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        dataSource = new DataSource(context);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String action = request.getParameter("action");

    // Pull the itemId from the form, not from session
    String itemIdParam = request.getParameter("itemId");
    if (itemIdParam == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing itemId");
        return;
    }
    int itemId = Integer.parseInt(itemIdParam);

    try (Connection connection = dataSource.getConnection()) {
        switch (action) {
            case "Sale"     -> worker.updateInventoryFlags(connection, itemId, true, false);
            case "Donation" -> worker.updateInventoryFlags(connection, itemId, false, true);
            case "Both"     -> worker.updateInventoryFlags(connection, itemId, true, true);
            default -> {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                return;
            }
        }
    } catch(SQLException e) {
        throw new ServletException(e);
    }

    // Redirect back to your RetailerView
    String target = request.getContextPath() + "/views/RetailerView.jsp";
    response.sendRedirect(response.encodeRedirectURL(target));
}

}