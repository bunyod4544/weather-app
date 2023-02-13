package com.example.weatherapp.exceptions;

import lombok.Getter;

/**
 * @author Doston Bokhodirov on 05 November 2022 at 6:39 PM
 */

@Getter
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
