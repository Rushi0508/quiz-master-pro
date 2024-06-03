package com.quizmasterpro.quizmaterpro.Models;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;

    private String name;
    private String username;
    private String password;
    private String email;

    @DBRef
    private List<Quiz> quizzes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
