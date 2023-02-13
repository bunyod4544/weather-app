package com.example.weatherapp.exceptions;

/**
 * @author Doston Bokhodirov on 01 December 2022 at 12:24 AM
 */

public class CustomFirebaseException extends RuntimeException {

    public CustomFirebaseException(String message) {
        super(message);
    }

}
