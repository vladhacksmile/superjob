package com.vladhacksmile.searchjob.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladhacksmile.searchjob.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mail")
        })
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, message = "Name should be min 2 symbols!")
    private String name;
    @NotEmpty(message = "Surname should not be empty!")
    @Size(min = 2, message = "Name should be contains min 2 symbols!")
    private String surname;
    private String patronymic;
    @Min(value = 0, message = "Age should be greater than 0!")
    private int age;
    @NotEmpty(message = "Number should not be empty!")
    @Size(min = 10, message = "Number should be contains min 10 symbols!")
    private String number;
    @NotEmpty(message = "Mail should not be empty!")
    @Size(min = 5, message = "Mail should be contains min 5 symbols!")
    @Email(message = "Incorrect mail format!")
    private String mail;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    public User(String name, String surname, String patronymic, int age, String number, String mail, String password, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
