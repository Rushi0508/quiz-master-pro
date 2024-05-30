package com.quizmasterpro.quizmaterpro.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
}
