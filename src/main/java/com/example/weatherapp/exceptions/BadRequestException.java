package com.example.weatherapp.exceptions;

/**
 * @author Doston Bokhodirov on 05 November 2022 at 6:52 PM
 */

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
