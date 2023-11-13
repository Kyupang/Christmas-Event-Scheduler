package christmas;

import christmas.domain.DiscountResult;
import christmas.domain.EventBadge;
import christmas.domain.Order;
import christmas.service.DiscountCalculatorService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class DecemberEventPlanner {
    private int expectedVisitDay;
    private List<Order> orders;
    private List<DiscountResult> discountResults;
    private int totalBenefitsAmount;
    private int totalOrderAmountBeforeDiscount;

    private final OrderService orderService;
    private final DiscountCalculatorService discountCalculatorService;

    public DecemberEventPlanner(OrderService orderService, DiscountCalculatorService discountCalculatorService) {
        this.orderService = orderService;
        this.discountCalculatorService = discountCalculatorService;
    }

    public void run() {
        inputVisitDay();
        inputOrder();
        printOrders();
        calculateTotalOrderAmountBeforeDiscount();
        checkGiftMenu();
        calculateBenefitsDetails();
        printTotalBenefitsAmount();
        calculateTotalOrderAmountAfterDiscount();
        checkEventBadge();
    }

    private void inputVisitDay() {
       boolean validInput = false;
        while (!validInput) {
            try {
                String expectedVisitDayInput = InputView.inputExpectedDate();
                //VisitDayInputValidator.validate(expectedVisitDay);
                expectedVisitDay = Integer.parseInt(expectedVisitDayInput);
                validInput = true;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void inputOrder() {
        boolean validInput = false;
        while (!validInput) {
            try {
                String menuAndQuantityInput = InputView.inputMenuAndQuantity();
                //MenuAndQuantityValidator.validate(menuAndQuantityInput);
                //Map menuAndQuantity = 주의사항(menuAndQuantityInput);
                //파싱하고
                //Order로 만드는 클래스 함수(menuAndQuantity);
                String[] orderTokens = menuAndQuantityInput.split(",");
                for (String orderToken : orderTokens) {
                    String[] parts = orderToken.split("-");
                    if (parts.length == 2) {
                        String menu = parts[0].trim();
                        int quantity = Integer.parseInt(parts[1].trim());

                        Order order = new Order(menu, quantity);
                        orderService.recordOrder(order);
                    }
                }

                //Service로 보내는것도 저기서 하자
                validInput = true;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    // 주문 메뉴 출력하기 (주문메뉴 리스트) OrderService.getOrderRecord 하면 될듯
    private void printOrders() {
        orders = orderService.getAllOrderRecord();
        OutputView.printOrderedMenuAndQuantity(orders);
    }
    // 주문메뉴의 가격을 가지고 총 계산 금액 계산하고 출력하기 (총 계산금액) OrderService.getOrderRecord에서 가격 가져와서 출력
    private void calculateTotalOrderAmountBeforeDiscount() {
        orders = orderService.getAllOrderRecord();
        totalOrderAmountBeforeDiscount = discountCalculatorService.calculateTotalOrderAmountBeforeDiscount(orders);
        OutputView.printTotalPaymentBeforeDiscount(totalOrderAmountBeforeDiscount);
    }
    // 총금액으로 증정 메뉴 있는지 없는지(총금액) 판단 후 증정내역 출력하기
    private void checkGiftMenu() {
        orders = orderService.getAllOrderRecord();
        if(discountCalculatorService.applyChampagneGift(totalOrderAmountBeforeDiscount)) {
            OutputView.printGiftMenu();
        }
    }

    // 할인 정책(날짜)에 따라 할인 적용하고 할인 내역 출력하기 result = DiscountCalculatorService.discount();
    // OutputView.resultPrint(result);
    private void calculateBenefitsDetails() {
        discountResults = discountCalculatorService.calculateDiscounts(expectedVisitDay);
        OutputView.printBenefitDetail(discountResults);
    }

    // 할인 혜택 적용에 따른 혜택 금액 계산(할인 내역) 후 출력하기 result 의 Discount Amount 를 for문 돌려서 더해
    private void printTotalBenefitsAmount() {
        totalBenefitsAmount = discountCalculatorService.calculateTotalBenefitAmount(discountResults);
        OutputView.printTotalBenefitsAmount(totalBenefitsAmount);
    }
    // 할인 후 금액(할인내역) 계산하고 출력하기 (총금액 - 혜택 금액)
    private void calculateTotalOrderAmountAfterDiscount() {
        int totalOrderAmountAfterDiscount = totalOrderAmountBeforeDiscount - totalBenefitsAmount;
        if (discountCalculatorService.applyChampagneGift(totalOrderAmountBeforeDiscount)) {
            totalOrderAmountAfterDiscount += 25000;
        }
        OutputView.printExpectedPaymentAfterDiscount(totalOrderAmountAfterDiscount);
    }
    // 할인 금액으로 12월 베지 확인하고 출력하기 (혜택금액 넘겨서 뱃지 받자)

    private void checkEventBadge() {
        EventBadge eventBadge = EventBadge.calculateEventBadge(totalBenefitsAmount);
        OutputView.printDecemberEventBadge(eventBadge);
    }


}
