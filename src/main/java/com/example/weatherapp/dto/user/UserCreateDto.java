package com.example.weatherapp.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserCreateDto {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 8, message = "Size of username should be min 8")
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Size of password should be min 8")
    private String password;
    @NotBlank(message = "Full name is mandatory")
    @Size(min = 8, message = "Size of full name should be min 8")
    private String fullName;
    @NotNull(message = "Role is mandatory")
    private Long roleId;

}
