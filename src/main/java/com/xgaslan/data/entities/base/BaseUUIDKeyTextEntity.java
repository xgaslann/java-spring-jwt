package com.xgaslan.data.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseUUIDKeyTextEntity extends BaseTextEntity {

    @Column(nullable = false)
    private Long objectId;
}
