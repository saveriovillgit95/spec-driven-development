package com.myproject.sdd.sdd_demo.controller;

import com.myproject.model.OrderDto;
import com.myproject.sdd.sdd_demo.exception.GlobalExceptionHandler;
import com.myproject.sdd.sdd_demo.exception.OrderNotFoundException;
import com.myproject.sdd.sdd_demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@Import(GlobalExceptionHandler.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    void getOrderById_whenExists_returns200WithBody() throws Exception {
        OrderDto dto = new OrderDto();
        dto.setId(1);
        dto.setName("Mario");
        dto.setSurname("Rossi");
        dto.setEmail("mario.rossi@example.com");
        dto.setAge(30);

        when(orderService.getOrder(1)).thenReturn(dto);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mario"))
                .andExpect(jsonPath("$.surname").value("Rossi"))
                .andExpect(jsonPath("$.email").value("mario.rossi@example.com"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    void getOrderById_whenNotFound_returns404() throws Exception {
        when(orderService.getOrder(99)).thenThrow(new OrderNotFoundException(99));

        mockMvc.perform(get("/orders/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Order Not Found"))
                .andExpect(jsonPath("$.detail").value("Order not found with id: 99"));
    }
}
