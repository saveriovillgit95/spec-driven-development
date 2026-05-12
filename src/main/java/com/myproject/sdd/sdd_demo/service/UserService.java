package com.myproject.sdd.sdd_demo.service;

import com.myproject.model.UserDto;
import com.myproject.sdd.sdd_demo.entity.User;
import com.myproject.sdd.sdd_demo.exception.UserNotFoundException;
import com.myproject.sdd.sdd_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return toDto(user);
    }

    // --- mapping ---

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        return dto;
    }
}
