package com.library_managment.user.model.entity;

import com.library_managment.user.model.entity.base.EffectiveEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Access(AccessType.PROPERTY)
public class Resource extends EffectiveEntity {

    private String name;
    private String description;

    private List<Role> roles;

    @Override
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_seq")
    @SequenceGenerator(name="resource_seq",sequenceName="RESOURCE_SEQ", allocationSize=50)
    public Long getId() {
        return super.getId();
    }


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
