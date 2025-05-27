package com.xgaslan.data.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseTextEntity extends BaseNumericKeyEntity {

    @Column(nullable = false, length = 5)
    private String languageId;

    @Column(nullable = false)
    private String text;
}