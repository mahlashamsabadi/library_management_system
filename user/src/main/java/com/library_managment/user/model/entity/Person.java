package com.library_managment.user.model.entity;

import com.library_managment.user.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;
    private String nationalCode;
    private String phone;
    private Date birthDate;

    private User user;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser(){
        return this.user;
    }

}
