package com.myproject.sdd.sdd_demo.service;

import com.myproject.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

        public UserDto getUser(Integer id) {

            UserDto dto = new UserDto();

            dto.setId(id);
            dto.setName("Mario Rossi");

            return dto;
        }

}
