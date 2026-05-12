package com.myproject.sdd.sdd_demo.service;

import com.myproject.model.UserDto;
import com.myproject.sdd.sdd_demo.entity.User;
import com.myproject.sdd.sdd_demo.exception.UserNotFoundException;
import com.myproject.sdd.sdd_demo.repository.UserRepository;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUser_whenExists_returnsDto() {
        User user = User.builder()
                .id(1)
                .name("Mario")
                .surname("Rossi")
                .email("mario.rossi@example.com")
                .age(30)
                .build();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserDto result = userService.getUser(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Mario");
        assertThat(result.getEmail()).isEqualTo("mario.rossi@example.com");
    }

    @Test
    void getUser_whenNotFound_throwsUserNotFoundException() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUser(99))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("99");
    }
}
