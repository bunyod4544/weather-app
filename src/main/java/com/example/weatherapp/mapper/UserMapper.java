package com.example.weatherapp.mapper;

import com.example.weatherapp.dto.user.UserCreateDto;
import com.example.weatherapp.dto.user.UserDto;
import com.example.weatherapp.dto.user.UserUpdateDto;
import com.example.weatherapp.entity.AuthUser;
import com.example.weatherapp.entity.Role;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
@Component
public abstract class UserMapper {
    protected PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    public abstract AuthUser toEntity(UserCreateDto dto);

    @Mapping(target = "id", ignore = true)
    public abstract void toEntity(UserUpdateDto dto, @MappingTarget AuthUser entity);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapToIds")
    public abstract UserDto fromEntity(AuthUser user);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(newPassword))")
    public abstract void fromPassword(String newPassword, @MappingTarget AuthUser user);

    @Named("mapToIds")
    List<Long> mapToIds(List<Role> roles) {
        if (!roles.isEmpty()) {
            List<Long> responses = new ArrayList<>();
            roles.forEach(role -> {
                if (Objects.nonNull(role)) {
                    responses.add(role.getId());
                }
            });
            return responses;
        }

        return new ArrayList<>();
    }
}
