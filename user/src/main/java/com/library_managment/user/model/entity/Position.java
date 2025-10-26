package com.library_managment.user.model.entity;

import com.library_managment.user.model.entity.base.EffectiveEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Access(AccessType.PROPERTY)
public class Position extends EffectiveEntity {

    private String title;

    private List<ApplicationUser> users;

    private List<Role> roles;

    @Override
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_seq")
    @SequenceGenerator(name="position_seq",sequenceName="POSITION_SEQ", allocationSize=50)
    public Long getId() {
        return super.getId();
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_position",
            joinColumns = @JoinColumn(name = "position_id"), // Foreign key for Book
            inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key for Author
    )
    public List<ApplicationUser> getUsers() {
        return users;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "position_role",
            joinColumns = @JoinColumn(name = "position_id"), // Foreign key for Book
            inverseJoinColumns = @JoinColumn(name = "role_id") // Foreign key for Author
    )
    public List<Role> getRoles() {
        return roles;
    }

}
