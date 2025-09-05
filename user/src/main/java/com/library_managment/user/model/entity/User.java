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
public class User extends EffectiveEntity {

    private String username;
    private String password;
    private String email;

    private List<Role> roles;

    private Person person;

    @Override
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=50)
    public Long getId() {
        return super.getId();
    }

    @NonNull
    @Column(unique = true)
    public String getUsername(){
        return this.username;
    }

    @Column(name = "email", nullable = false, unique = true, length = 100)
    public String getEmail(){
        return this.email;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key for Book
            inverseJoinColumns = @JoinColumn(name = "role_id") // Foreign key for Author
    )
    public List<Role> getRoles(){
        return this.roles;
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Person getPerson(){
        return this.person;
    }


}
