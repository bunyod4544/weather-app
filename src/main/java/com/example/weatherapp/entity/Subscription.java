package com.example.weatherapp.entity;

import com.example.weatherapp.entity.base.Auditable;
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
@Table(name = "subscriptions")
public class Subscription extends Auditable {

    @ManyToOne( fetch = FetchType.LAZY)
    private AuthUser user;

    @ManyToOne( fetch = FetchType.LAZY)
    private City city;

    @Column(name = "comment")
    private String comment;
}
