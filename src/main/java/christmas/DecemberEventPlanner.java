package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class DecemberEventPlanner {
    public void run() {
        inputVisitDay();
        inputOrder();
    }

    private void inputVisitDay() {
       boolean validInput = false;
        while (!validInput) {
            try {
                String expectedVisitDayInput = InputView.inputExpectedDate();
                //VisitDayInputValidator.validate(expectedVisitDay);
                //expectedVisitDay = Integer.parseInt(expectedVisitDayInput);
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
                //Order로 만드는 클래스 함수(menuAndQuantity);
                //Service로 보내는것도 저기서 하자
                validInput = true;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }


    // 주문 메뉴 출력하기 (주문메뉴 리스트) OrderService.getOrderRecord 하면 될듯

    // 주문메뉴의 가격을 가지고 총 계산 금액 계산하고 출력하기 (총 계산금액) OrderService.getOrderRecord에서 가격 가져와서 출력

    // 총금액으로 증정 메뉴 있는지 없는지(총금액) 판단 후 증정내역 출력하기

    // 할인 정책(날짜)에 따라 할인 적용하고 할인 내역 출력하기 result = DiscountCalculatorService.discount();
    // OutputView.resultPrint(result);

    // 할인 혜택 적용에 따른 혜택 금액 계산(할인 내역) 후 출력하기 result 의 Discount Amount 를 for문 돌려서 더해

    // 할인 후 금액(할인내역) 계산하고 출력하기 (총금액 - 혜택 금액)

    // 할인 금액으로 12월 베지 확인하고 출력하기 (혜택금액 넘겨서 뱃지 받자)


}
