package com.example.weatherapp.service.user;

import com.example.weatherapp.dto.user.UserCreateDto;
import com.example.weatherapp.dto.user.UserDto;
import com.example.weatherapp.exceptions.BadRequestException;
import com.example.weatherapp.response.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> create(UserCreateDto dto) throws BadRequestException;
}
