package com.myproject.sdd.sdd_demo.service;

import com.myproject.model.OrderDto;
import com.myproject.sdd.sdd_demo.entity.Order;
import com.myproject.sdd.sdd_demo.exception.OrderNotFoundException;
import com.myproject.sdd.sdd_demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void getOrder_whenExists_returnsDto() {
        Order order = Order.builder()
                .id(1)
                .name("Mario")
                .surname("Rossi")
                .email("mario.rossi@example.com")
                .age(30)
                .build();

        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        OrderDto result = orderService.getOrder(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Mario");
        assertThat(result.getSurname()).isEqualTo("Rossi");
        assertThat(result.getEmail()).isEqualTo("mario.rossi@example.com");
        assertThat(result.getAge()).isEqualTo(30);
    }

    @Test
    void getOrder_whenNotFound_throwsOrderNotFoundException() {
        when(orderRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.getOrder(99))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("99");
    }
}
