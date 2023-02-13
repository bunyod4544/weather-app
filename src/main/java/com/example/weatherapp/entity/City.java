package com.example.weatherapp.entity;

import com.example.weatherapp.entity.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class City extends Auditable {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(targetEntity = Weather.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Weather weather;

    @Column(name = "weather_id")
    private Long weatherId;

    @Column(name = "is_hide")
    private Boolean isHide;

    @OneToMany(mappedBy = "city")
    private List<Subscription> subscriptionList;
}
