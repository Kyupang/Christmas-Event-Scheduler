package christmas.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.repository.MemoryOrderRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class OrderRepositoryTest {
    private MemoryOrderRepository memoryOrderRepository;

    @BeforeEach
    void setUp() {
        memoryOrderRepository = new MemoryOrderRepository();
    }

    @DisplayName("메모리 저장소에 저장하고 불러오는 테스트")
    @Test
    void saveAndGetOrderList() {
        Order order1 = new Order("티본스테이스", 1);
        Order order2 = new Order("파스타", 2);

        memoryOrderRepository.save(order1);
        memoryOrderRepository.save(order2);
        List<Order> orderList = memoryOrderRepository.getOrderedList();

        assertThat(orderList).contains(order1,order2);
    }
}
