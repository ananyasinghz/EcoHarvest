package controller;

import businesslayer.RetailerInventoryBusinessLogic;
import dataaccesslayer.DataSource;
import model.DTOBuilder;
import model.RetailerInventoryWorker;
import notifications.NotificationService;
import transferobjects.InventoryItemDTO;
import transferobjects.ItemDTO;
import transferobjects.RetailerInventoryDTO;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This servlet is used to add, update, or view the inventory of a retailer
 */
@WebServlet(name = "RetailerInventoryServlet", urlPatterns = {"/RetailerInventoryServlet"})
public class RetailerInventoryServlet extends HttpServlet {
    int AUTO_SALE = 1;
    int AUTO_DONATION = 2;
    String action;
    int retailId;
    int itemId;
    String itemName;
    String itemCategory;
    int rInventoryBatchNum;
    int rInventoryQuantity;
    double rInventoryUnitPrice;
    double rInventoryFinalPrice;
    Date rInventoryExpDate;
    boolean rInventorysale = false;
    boolean rInventoryDonation = false;
    boolean setSurplusFlag = false;
    RetailerInventoryWorker worker = new RetailerInventoryWorker();
    DTOBuilder builder = new DTOBuilder();

    DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        dataSource = new DataSource(context);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            // no session or no login → redirect to sign‑in
            String login = request.getContextPath() + "/views/SignIn.jsp"; // or wherever your login really is
            response.sendRedirect(response.encodeRedirectURL(login));
            return;
        }
        int retailId = (Integer) session.getAttribute("userId");

        action = request.getParameter("action");
        retailId = (int) request.getSession().getAttribute("userId");
        if (action.equals("Add Item")) {
            itemName = request.getParameter("productName");
            itemCategory = request.getParameter("category");
            rInventoryBatchNum = Integer.parseInt(request.getParameter("batch"));
            rInventoryQuantity = Integer.parseInt(request.getParameter("quantity"));
            rInventoryUnitPrice = Double.parseDouble(request.getParameter("price"));
            String expiryDateStr = request.getParameter("expiryDate");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            try {
                rInventoryExpDate = formatter.parse(expiryDateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try (Connection connection = dataSource.getConnection()) {
                if(worker.productAlreadyExists(connection, retailId, itemName, rInventoryBatchNum)){
                    String errorMessage = "Item already exists for this retailer.";
                    request.setAttribute("errorMessage", errorMessage);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/RetailerView.jsp");
                    dispatcher.forward(request, response);
                }else {
                    int productStatus = worker.productStatus(rInventoryExpDate); // determine how close to expiry we are
                    rInventoryFinalPrice = worker.productPrice(productStatus, rInventoryUnitPrice);
                    ItemDTO item = builder.itemBuilder(itemName, itemCategory);
                    itemId = worker.insertAndGetGeneratedId(connection, item);
                    if(productStatus == AUTO_SALE){
                        rInventorysale = true;
                    }
                    else if(productStatus == AUTO_DONATION){
                        rInventoryDonation = true;
                    }
                    else{
                        rInventorysale = false;
                        rInventoryDonation = false;
                        session.setAttribute("itemId", itemId);
                        setSurplusFlag = true;
                    }
                    RetailerInventoryDTO retailInventory = builder.retailerInventoryBuilder(retailId, itemId, rInventoryBatchNum, rInventoryQuantity, rInventoryUnitPrice, rInventoryFinalPrice, rInventoryExpDate, rInventorysale, rInventoryDonation);
                    RetailerInventoryBusinessLogic retailerInventoryBusinessLogic = new RetailerInventoryBusinessLogic(connection);
                    retailerInventoryBusinessLogic.addRetailerInventory(retailInventory);
                    NotificationService notificationService = new NotificationService();
                    notificationService.notifyObservers(retailInventory, connection);

                    if(setSurplusFlag){
                        RequestDispatcher dispatcher = request.getRequestDispatcher("views/SurplusItemChoice.jsp");
                        dispatcher.forward(request, response);
                        //Notifications


                    }
                    else {
                        response.sendRedirect("views/RetailerView.jsp");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equals("Update Item")) {
            request.setAttribute("retailId", retailId);
            response.sendRedirect("views/UpdateInventory.jsp");
        } else {
            try (Connection connection = dataSource.getConnection()) {
                request.setAttribute("retailId", retailId);
                List<InventoryItemDTO> inventory = worker.retrieveInventory(connection, retailId);
                request.setAttribute("inventory", inventory);

                RequestDispatcher dispatcher = request.getRequestDispatcher("views/RetailerInventory.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
