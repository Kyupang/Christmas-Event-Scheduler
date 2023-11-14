package christmas.service;

import christmas.discount.DiscountPolicy;
import christmas.domain.DiscountResult;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.repository.OrderRepository;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DiscountCalculatorServiceImpl implements DiscountCalculatorService {
    private final List<DiscountPolicy> discountPolicies;
    private final OrderRepository orderRepository;

    public DiscountCalculatorServiceImpl(List<DiscountPolicy> discountPolicies, OrderRepository orderRepository) {
        this.discountPolicies = discountPolicies;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<DiscountResult> calculateDiscounts(int expectedDate) {
        List<DiscountResult> discountResults = new ArrayList<>();

        List<Order> orderList = orderRepository.getOrderedList();

        LocalDate currentDate = LocalDate.of(2023, Month.DECEMBER, 1).plusDays(expectedDate - 1);

        for (DiscountPolicy discountPolicy : discountPolicies) {
            if (discountPolicy.isApplicable(currentDate)) {
                int discountAmount = discountPolicy.discount(currentDate, orderList);
                String discountName = discountPolicy.getDiscountName();

                discountResults.add(new DiscountResult(discountName, discountAmount));
            }
        }
        if (applyChampagneGift(calculateTotalOrderAmountBeforeDiscount(orderList))) {
            discountResults.add(new DiscountResult("증정 이벤트", 25000));
        }
        return discountResults;
    }

    @Override
    public int calculateTotalOrderAmountBeforeDiscount(List<Order> orders) {
        int totalAmount = 0;
        for (Order order : orders) {
            int price = Menu.valueOf(order.getMenuName()).getPrice();
            totalAmount += price * order.getQuantity();
        }
        return totalAmount;
    }

    @Override
    public boolean applyChampagneGift(int totalOrderAmountBeforeDiscount) {
        return totalOrderAmountBeforeDiscount > 120000;
    }

    public int calculateTotalBenefitAmount(List<DiscountResult> results) {
        return results.stream()
                .mapToInt(DiscountResult::getDiscountAmount)
                .sum();
    }
}
