package com.example.weatherapp.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 8, message = "Size of username should be min 8")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Size of password should be min 8")
    private String password;


}
