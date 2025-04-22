package controller;
import businesslayer.UserAccountBusinessLogic;
import dataaccesslayer.DataSource;
import transferobjects.UserAccountDTO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * ProcessAddMoneyServlet class is a servlet that processes the addition of money to the user's account.
 */
@WebServlet(name = "ProcessAddMoneyServlet", urlPatterns = {"/ProcessAddMoneyServlet"})
public class ProcessAddMoneyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        try {
            double amountToAdd = Double.parseDouble(request.getParameter("amount"));
            
            if (userId == null || amountToAdd <= 0) {
                // Changed redirect to Signin.jsp instead of login.jsp
                response.sendRedirect(request.getContextPath() + "/views/Signin.jsp");
                return;
            }
            
            DataSource dataSource = new DataSource(getServletContext());
            try (Connection connection = dataSource.getConnection()) {
                UserAccountBusinessLogic userAccountBusinessLogic = new UserAccountBusinessLogic(connection);
                UserAccountDTO userAccountDTO = userAccountBusinessLogic.getUserAccountById(userId);
                
                if (userAccountDTO != null) {
                    // Add debug logging
                    System.out.println("Current balance: " + userAccountDTO.getBalance() + ", Adding: " + amountToAdd);
                    
                    double newBalance = userAccountDTO.getBalance() + amountToAdd;
                    userAccountDTO.setBalance(newBalance);
                    userAccountBusinessLogic.updateUserAccount(userAccountDTO);
                    
                    // Set both session attributes
                    session.setAttribute("userBalance", newBalance);
                    session.setAttribute("currentBal", newBalance);
                    
                    System.out.println("Updated balance: " + newBalance + " for user ID: " + userId);
                    
                    // Redirect instead of forward to avoid form resubmission
                    String target = request.getContextPath() + "/views/CustomerView.jsp";
                    response.sendRedirect(response.encodeRedirectURL(target));

                } else {
                    System.err.println("User account not found for ID: " + userId);
                    request.setAttribute("error", "Account not found");
                    request.getRequestDispatcher("/views/AddMoney.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid amount entered");
            request.getRequestDispatcher("/views/AddMoney.jsp").forward(request, response);
        }
    }
}