package com.myproject.sdd.sdd_demo.controller;

import com.myproject.model.UserDto;
import com.myproject.sdd.sdd_demo.exception.GlobalExceptionHandler;
import com.myproject.sdd.sdd_demo.exception.UserNotFoundException;
import com.myproject.sdd.sdd_demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(GlobalExceptionHandler.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void getUserById_whenExists_returns200WithBody() throws Exception {
        UserDto dto = new UserDto();
        dto.setId(1);
        dto.setName("Mario");
        dto.setEmail("mario.rossi@example.com");

        when(userService.getUser(1)).thenReturn(dto);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mario"))
                .andExpect(jsonPath("$.email").value("mario.rossi@example.com"));
    }

    @Test
    void getUserById_whenNotFound_returns404() throws Exception {
        when(userService.getUser(99)).thenThrow(new UserNotFoundException(99));

        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("User Not Found"))
                .andExpect(jsonPath("$.detail").value("User not found with id: 99"));
    }
}
