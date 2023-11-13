package christmas.discount;

import christmas.domain.Menu;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekendDiscountPolicy implements DiscountPolicy {
    private static final String POLICY_NAME = "주말 할인";
    private static final int DISCOUNT_AMOUNT_PER_ITEM = 2023;

    @Override
    public int discount(LocalDate currentDate, List<Order> orderList) {
        int totalDiscountAmount = 0;
        for (Order order : orderList) {
            if (Menu.valueOf(order.getMenuName()).getCategory().equals("메인")) {
                totalDiscountAmount += DISCOUNT_AMOUNT_PER_ITEM * order.getQuantity();
            }
        }
        return totalDiscountAmount;
    }

    @Override
    public boolean isApplicable(LocalDate currentDate) {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    @Override
    public String getDiscountName() {
        return POLICY_NAME;
    }

}
