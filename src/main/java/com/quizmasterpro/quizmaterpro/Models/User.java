package com.quizmasterpro.quizmaterpro.Models;

import java.util.List;
import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Quiz> quizzes;
}
