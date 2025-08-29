package com.library_managment.user.model.entity;

import com.library_managment.user.model.entity.base.EffectiveEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Resource extends EffectiveEntity {

    private String name;
    private String description;

    private List<Role> roles;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "resource_role" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public List<Role> getRoles(){
        return this.roles;
    }

}
