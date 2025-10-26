package com.library_managment.user.model.entity.base;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class EffectiveEntity extends CreationEntity{

    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;

}
