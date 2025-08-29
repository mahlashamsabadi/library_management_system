package com.library_managment.user.model.entity;

import com.library_managment.user.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BookStatus extends BaseEntity {

    private String code;
    private String name;
    private String description;

    @Id
    @Override
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=50)
    public Long getId() {
        return super.getId();
    }

}
