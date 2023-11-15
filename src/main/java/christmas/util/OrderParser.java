package christmas.util;

import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderParser {
    public static List<Order> parseOrders(String menuAndQuantityInput) {
        List<Order> orders = new ArrayList<>();
        String[] orderTokens = menuAndQuantityInput.split(",");

        for (String orderToken : orderTokens) {
            String[] parts = orderToken.split("-");
            if (parts.length == 2) {
                String menu = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());

                Order order = new Order(menu, quantity);
                orders.add(order);
            }
        }
        return orders;
    }
}
