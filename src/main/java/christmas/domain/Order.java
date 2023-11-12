package christmas.domain;

import java.util.Map;

public class Order {
    private String menuName;
    private int quantity;
    public Order(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }
}
