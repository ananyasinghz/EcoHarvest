package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Servlet shows the summary of items that user wants to buy and checkout.
 */
@WebServlet(name = "ProcessCheckoutServlet", value = "/ProcessCheckoutServlet")
public class ProcessCheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = new ArrayList<>();
        double totalCost = 0.0;



        try {
            String[] itemParams = request.getParameterValues("items[].");
            if (itemParams == null) {
                throw new IllegalArgumentException("No items found in the request.");
            }

            int itemsPerEntry = 7;
            for (int i = 0; i < itemParams.length; i += itemsPerEntry) {
                String itemName = itemParams[i];
                String expiryDate = itemParams[i + 2];
                boolean onSale = "true".equals(itemParams[i + 3]);
                double unitPrice = Double.parseDouble(itemParams[i + 4]);
                int quantityPurchased = Integer.parseInt(itemParams[i + 5]);
                double cost = Double.parseDouble(itemParams[i + 6]);
                int retailerInventoryID = Integer.parseInt(itemParams[i + 1]); // Parsing the retailerInventoryID

                Item item = new Item(itemName, expiryDate, onSale, unitPrice, quantityPurchased, cost, retailerInventoryID);
                items.add(item);

                totalCost += cost;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request: No items found.");
            return;
        }


        request.setAttribute("items", items);
        request.setAttribute("totalCost", totalCost);

        // Forward to the summary page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/checkoutSummary.jsp");
        dispatcher.forward(request, response);
    }


    public static class Item {
        private String itemName;
        private String expiryDate;
        private boolean onSale;
        private double unitPrice;
        private int quantityPurchased;
        private double totalCost;
        private int retailerInventoryID;


//        Need this item class to display selective details in the checkout page
        public Item(String itemName, String expiryDate, boolean onSale, double unitPrice, int quantityPurchased, double totalCost, int retailerInventoryID) {
            this.itemName = itemName;
            this.expiryDate = expiryDate;
            this.onSale = onSale;
            this.unitPrice = unitPrice;
            this.quantityPurchased = quantityPurchased;
            this.totalCost = totalCost;
            this.retailerInventoryID = retailerInventoryID;
        }


        public String getItemName() { return itemName; }
        public String getExpiryDate() { return expiryDate; }
        public boolean isOnSale() { return onSale; }
        public double getUnitPrice() { return unitPrice; }
        public int getQuantityPurchased() { return quantityPurchased; }
        public double getTotalCost() { return totalCost; }
        public int getRetailerInventoryID() { return retailerInventoryID; } // Getter for the retailerInventoryID
    }
}
