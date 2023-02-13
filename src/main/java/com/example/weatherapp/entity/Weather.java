package com.example.weatherapp.entity;

import com.example.weatherapp.entity.base.Auditable;
import com.example.weatherapp.enums.MetricType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather")
public class Weather extends Auditable {

    @Column(name = "temperature")
    private Integer temperature;

    @Column(name = "pressure")
    private Integer pressure;

    @Column(name = "metric_type")
    @Enumerated(EnumType.STRING)
    private MetricType metricType = MetricType.CELSIUS;

}
