package com.library_managment.user.model.entity.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_system_sequence")
    @SequenceGenerator(name="library_system_sequence",sequenceName="library_system_sequence", allocationSize=1)
    private Long id;

}
