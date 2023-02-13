package com.example.weatherapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetricType {

    CELSIUS("°C"),
    FAHRENHEIT("°F");
    private final String description;
}
