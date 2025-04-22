package controller;

import businesslayer.RetailerInventoryBusinessLogic;
import businesslayer.TransactionBusinessLogic;
import businesslayer.UserAccountBusinessLogic;
import dataaccesslayer.DataSource;
import transferobjects.RetailerInventoryDTO;
import transferobjects.TransactionDTO;
import transferobjects.UserAccountDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * The Servlet processes the payment upon checkout.
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/views/SignIn.jsp");
            return;
        }
        ServletContext context = getServletContext();
        DataSource dataSource = new DataSource(context);

        try {
            Connection connection = dataSource.getConnection();
            Double totalCost = Double.valueOf(request.getParameter("totalCost"));
            System.out.println("Total Cost: " + totalCost);
            Integer customerId = (Integer) session.getAttribute("userId");
            if (customerId == null) {
                response.sendRedirect(request.getContextPath() + "/views/SignIn.jsp");
                return;
            }
            UserAccountBusinessLogic userAccountBusinessLogic = new UserAccountBusinessLogic(connection);
            UserAccountDTO userAccountDTO = userAccountBusinessLogic.getUserAccountById(customerId);
            double newBalance = userAccountDTO.getBalance()-totalCost;
            userAccountDTO.setBalance(newBalance);
            userAccountBusinessLogic.updateUserAccount(userAccountDTO);

            request.getParameterMap().forEach((paramName, paramValues) -> {
                System.out.println(paramName + ": " + String.join(", ", paramValues));
            });

            String[] retailerInventoryIDs = request.getParameterValues("retailerInventoryIDs[]");
            String[] quantities = request.getParameterValues("quantities[]");

            HashMap<Integer, Integer> transactionDetails = new HashMap<>();

            if (retailerInventoryIDs != null && quantities != null) {
                for (int i = 0; i < retailerInventoryIDs.length; i++) {
                    int id = Integer.parseInt(retailerInventoryIDs[i]);
                    int qty = Integer.parseInt(quantities[i]);
                    RetailerInventoryBusinessLogic retailerInventoryBusinessLogic = new RetailerInventoryBusinessLogic(connection);
                    RetailerInventoryDTO retailerInventoryDTO = new RetailerInventoryDTO();
                    retailerInventoryDTO = retailerInventoryBusinessLogic.getRetailerInventoryById(id);
                    retailerInventoryDTO.setQuantity(retailerInventoryDTO.getQuantity()-qty);
                    System.out.println(retailerInventoryDTO);
                    retailerInventoryBusinessLogic.updateRetailerInventory(retailerInventoryDTO);
                    TransactionBusinessLogic transactionBusinessLogic = new TransactionBusinessLogic(connection);
                    TransactionDTO transactionDTO = new TransactionDTO();
                    transactionDTO.setQuantity(qty);
                    transactionDTO.setUsersId(customerId);
                    transactionDTO.setUserInventoryId(id);

                    transactionBusinessLogic.addTransaction(transactionDTO);

                }
            }

            session.setAttribute("userBalance",newBalance);

            session.setAttribute("userID",customerId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/CustomerView.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
