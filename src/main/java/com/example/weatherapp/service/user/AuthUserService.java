package com.example.weatherapp.service.user;

import com.example.weatherapp.dto.user.UserCreateDto;
import com.example.weatherapp.dto.user.UserDto;
import com.example.weatherapp.entity.AuthUser;
import com.example.weatherapp.exceptions.BadRequestException;
import com.example.weatherapp.mapper.UserMapper;
import com.example.weatherapp.repository.AuthUserRepository;
import com.example.weatherapp.repository.RoleRepository;
import com.example.weatherapp.response.ResponseEntity;
import com.example.weatherapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserService, UserDetailsService {

    private final AuthUserRepository repository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public ResponseEntity<UserDto> create(UserCreateDto dto) throws BadRequestException {
        if ( repository.existsByUsername(dto.getUsername()) ) {
            throw new BadRequestException("User with this username already exists");
        }
        AuthUser user = mapper.toEntity(dto);
        user.setRoles(List.of(roleRepository.getById(dto.getRoleId())));


        repository.save(user);
        return ResponseEntity.ok(mapper.fromEntity(user));
    }
}
