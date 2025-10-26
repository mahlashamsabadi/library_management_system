package com.library_managment.user.model.entity;

import com.library_managment.user.model.entity.base.EffectiveEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.PROPERTY)
public class Role extends EffectiveEntity {

    private String name;
    private String description;

    private List<ApplicationUser> users;
    private List<Resource> resources;
    private List<Position> positions;

    @Override
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name="role_seq",sequenceName="ROLE_SEQ", allocationSize=50)
    public Long getId() {
        return super.getId();
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    public List<ApplicationUser> getUsers(){
        return this.users;
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    public List<Resource> getResources(){
        return this.resources;
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    public List<Position> getPositions(){
        return this.positions;
    }

}
