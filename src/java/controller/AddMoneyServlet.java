package controller;
import businesslayer.UserAccountBusinessLogic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddMoneyServlet", urlPatterns = {"/AddMoneyServlet"})
public class AddMoneyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = null;
        double currentBal = 0;
        try {
            userId = Integer.parseInt(request.getParameter("userId"));
            String currentBalStr = request.getParameter("currentBal");
            
            // Add debug output
            System.out.println("AddMoneyServlet - User ID: " + userId + ", Balance param: " + currentBalStr);
            
            if (currentBalStr != null && !currentBalStr.isEmpty()) {
                currentBal = Double.parseDouble(currentBalStr);
            } else {
                // Get balance from session if parameter is missing
                HttpSession session = request.getSession();
                Object sessionBal = session.getAttribute("userBalance");
                if (sessionBal != null) {
                    currentBal = (Double) sessionBal;
                    System.out.println("Used session balance: " + currentBal);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing parameters: " + e.getMessage());
            // Fixed redirect to Signin.jsp
            response.sendRedirect(request.getContextPath() + "/views/Signin.jsp");
            return;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);
        session.setAttribute("currentBal", currentBal);
        
        System.out.println("Redirecting to AddMoney.jsp with userId: " + userId + ", currentBal: " + currentBal);
        response.sendRedirect(request.getContextPath() + "/views/AddMoney.jsp");
    }
}
