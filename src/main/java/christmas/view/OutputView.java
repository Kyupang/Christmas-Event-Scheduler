package christmas.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String PREVIEW_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDERED_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_PAYMENT_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String BENEFIT_DETAIL_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFITS_AMOUNT_MESSAGE = "<총혜택 금액>";
    private static final String EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String DECEMBER_EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    public static void printPreviewInformationalMessage() {
        System.out.println(PREVIEW_MESSAGE);
    }
    public static void printOrderedMenuAndQuantity() {
        System.out.println("\n" + ORDERED_MENU_MESSAGE);
    }
    public static void printTotalPaymentBeforeDiscount() {
        System.out.println("\n" + TOTAL_PAYMENT_BEFORE_DISCOUNT_MESSAGE);
    }
    public static void printGiftMenu() {
        System.out.println("\n" + GIFT_MENU_MESSAGE);
    }
    public static void printBenefitDetail() {
        System.out.println("\n" + BENEFIT_DETAIL_MESSAGE);
    }
    public static void printTotalBenefitsAmount() {
        System.out.println("\n" + TOTAL_BENEFITS_AMOUNT_MESSAGE);
    }
    public static void printExpectedPaymentAfterDiscount() {
        System.out.println("\n" + EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE);
    }
    public static void printDecemberBadge() {
        System.out.println("\n" + DECEMBER_EVENT_BADGE_MESSAGE);
    }

    public static void printErrorMessage(String detailErrorMessage) {
        System.out.println(ERROR_MESSAGE + detailErrorMessage);
    }
}
