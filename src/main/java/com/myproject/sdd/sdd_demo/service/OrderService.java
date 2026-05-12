package com.myproject.sdd.sdd_demo.service;

import com.myproject.model.OrderDto;
import com.myproject.sdd.sdd_demo.entity.Order;
import com.myproject.sdd.sdd_demo.exception.OrderNotFoundException;
import com.myproject.sdd.sdd_demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDto getOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return toDto(order);
    }

    // --- mapping ---

    private OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setName(order.getName());
        dto.setSurname(order.getSurname());
        dto.setEmail(order.getEmail());
        dto.setAge(order.getAge());
        return dto;
    }
}
