package com.library_managment.user.model.entity;

import com.library_managment.user.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.PROPERTY)
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;
    private String nationalCode;
    private String phone;
    private LocalDateTime birthDate;

    private ApplicationUser user;

    @Override
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name="person_seq",sequenceName="PERSON_SEQ", allocationSize=50)
    public Long getId() {
        return super.getId();
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public ApplicationUser getUser(){
        return this.user;
    }

}
