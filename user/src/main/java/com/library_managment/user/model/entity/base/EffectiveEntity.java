package com.library_managment.user.model.entity.base;

import lombok.Data;

@Data
public class EffectiveEntity extends CreationEntity{

    private Data effectiveDate;
    private Data disableDate;

}
