package com.example.weatherapp.controller;

import com.example.weatherapp.dto.login.LoginDto;
import com.example.weatherapp.dto.login.LoginResponse;
import com.example.weatherapp.dto.user.UserCreateDto;
import com.example.weatherapp.dto.user.UserDto;
import com.example.weatherapp.dto.user.UserUpdateDto;
import com.example.weatherapp.entity.AuthUser;
import com.example.weatherapp.exceptions.BadRequestException;
import com.example.weatherapp.response.ResponseEntity;
import com.example.weatherapp.security.JwtTokenUtils;
import com.example.weatherapp.service.user.UserService;
import com.example.weatherapp.utils.APIUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(APIUtils.API + APIUtils.V1 + APIUtils.AUTH)
@RequiredArgsConstructor
public class AuthUserController {

    private final UserService authUserService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping(APIUtils.LOGIN)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        String jwt = jwtTokenUtils.generateToken(authUser.getUsername(), authUser.getRoles());
        List<String> permissions = authUser.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new LoginResponse(jwt, permissions));
    }

    @PostMapping(APIUtils.CREATE)
    public ResponseEntity<UserDto> create(@RequestBody UserCreateDto dto) throws BadRequestException {
        return authUserService.create(dto);
    }

    @PutMapping(value = APIUtils.ID)
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping(value = APIUtils.ID)
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return ResponseEntity.ok(MessageKey.SUCCESS);
    }

    @GetMapping(value = APIUtils.ID)
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = APIUtils.EXCEL)
    public ResponseEntity<List<UserDTO>> getAll(@RequestBody UserCriteria criteria) {
        return ResponseEntity.ok(service.getAll(criteria));
    }

}
