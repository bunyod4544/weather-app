package com.example.weatherapp.entity.base;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable extends BaseEntity{


    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

}
