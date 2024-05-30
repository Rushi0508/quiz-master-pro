package com.quizmasterpro.quizmasterpro.Dtos.Topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TopicCreateDto {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;
}
