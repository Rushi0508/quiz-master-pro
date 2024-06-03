package com.quizmasterpro.quizmaterpro.Models;


import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "topics")
public class Topic {
    @Id
    private String id;
    private String name;
}
