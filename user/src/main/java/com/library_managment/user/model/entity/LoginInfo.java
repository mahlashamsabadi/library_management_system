package com.library_managment.user.model.entity;

import com.library_managment.user.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.PROPERTY)
public class LoginInfo extends BaseEntity {

    private String refresh;
    private ApplicationUser user;
    private LocalDateTime expirationDate;
    private LocalDateTime creationDate;



    @Override
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loginInfo_seq")
    @SequenceGenerator(name="loginInfo_seq",sequenceName="LOGIN_INFO_SEQ", allocationSize=50)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public ApplicationUser getUser(){
        return this.user;
    }

}
