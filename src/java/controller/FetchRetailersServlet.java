package controller;

import businesslayer.ShowRetailersAtLocationBusinessLogic;
import transferobjects.UserDTO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "FetchRetailersServlet", urlPatterns = {"/FetchRetailersServlet"})
public class FetchRetailersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession(false);

if (session == null) {
    System.out.println("Session is null in FetchRetailersServlet.");
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}

System.out.println("Session ID in FetchRetailersServlet: " + session.getId());

// Now you can safely check for userBalance
if (session.getAttribute("userBalance") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}


    double currentBal = (Double) session.getAttribute("userBalance");
    System.out.println("DEBUG - FetchRetailersServlet: session balance read as: " + currentBal);

    // Grab the location
    String location = request.getParameter("location").toLowerCase();
    ArrayList<UserDTO> retailerNames = new ArrayList<>();
    try {
        ShowRetailersAtLocationBusinessLogic logic = new ShowRetailersAtLocationBusinessLogic();
        retailerNames = logic.getRetailersAtLocations(location, getServletContext());
        for (UserDTO u : retailerNames) {
            System.out.println("Retailer location: " + u.getLocation());
        }
    } catch (SQLException e) {
        throw new ServletException(e);
    }

    // Now set everything and do a single forward
    request.setAttribute("currentBal", currentBal);
    request.setAttribute("retailerNames", retailerNames);
    request.getRequestDispatcher("/views/showRetailersAtLocation.jsp")
           .forward(request, response);
}
}
