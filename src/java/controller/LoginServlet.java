//loginservlet
package controller;

import businesslayer.UserAccountBusinessLogic;
import dataaccesslayer.DataSource;
import model.CharityWorker;
import model.LogInValidation;
import transferobjects.InventoryItemDTO;
import transferobjects.UserAccountDTO;
import transferobjects.UserDTO;

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
import java.util.List;
import java.util.Objects;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        dataSource = new DataSource(context);
    }
    
    LogInValidation logInValidation = new LogInValidation();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        
        if (Objects.equals(action, "Sign in")) {
            try (Connection connection = dataSource.getConnection()) {
                UserDTO user = logInValidation.getUserRoleAndId(username, password, connection);
                if (user != null) {
                    HttpSession session = request.getSession();
                    String nextPage = logInValidation.logInPageRedirect(action, user.getRole());
                    session.setAttribute("userId", user.getId());
                    session.setAttribute("userRole", user.getRole());
                    
                    if (user.getRole().equals("Customer")) {
                        UserAccountBusinessLogic userAccountBusinessLogic = new UserAccountBusinessLogic(connection);
                        UserAccountDTO userAccountDTO = userAccountBusinessLogic.getUserAccountById(user.getId());
                        
                        // If the user account is null, create a new one with default balance
                        if (userAccountDTO == null) {
                            userAccountDTO = new UserAccountDTO();
                            userAccountDTO.setUsersId(user.getId());
                            userAccountDTO.setBalance(1000.00); // Default balance
                            userAccountBusinessLogic.addUserAccount(userAccountDTO);
                            // Optionally, retrieve the account again to confirm insertion:
                            userAccountDTO = userAccountBusinessLogic.getUserAccountById(user.getId());
                        }
                        if (userAccountDTO != null) {
                        session.setAttribute("userBalance", userAccountDTO.getBalance());
                        session.setAttribute("currentBal", userAccountDTO.getBalance());
                        } else {
                         session.setAttribute("userBalance", 0.00);
                           }
                    System.out.println("Session ID in LoginServlet: " + session.getId());
                    // Now debug it *after* setting:
                    System.out.println("DEBUG loginservlet - Session balance finally set to: " + session.getAttribute("userBalance"));

                        
                    }
                    if (user.getRole().equals("Retailer")) {
                        session.setAttribute("userId", user.getId());
                    } else if(user.getRole().equals("Charity")){
                        CharityWorker worker = new CharityWorker();
                        List<InventoryItemDTO> items = worker.displayCharityClaims(connection);
                        session.setAttribute("items", items);
                    }
                    
                   // Keep the same request+session, no new cookie/session is created
                    request.getRequestDispatcher("/" + nextPage).forward(request, response);
                } else {
                    response.sendRedirect("views/SignInError.jsp");
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("views/register/role_selection.jsp");
        }
    }
}
