package com.quizmasterpro.quizmaterpro.Dtos.User;

import lombok.Data;

@Data
public class TokenResp {
    private String token;
    private String id;
    private String name;
    private String username;
    private String email;
}
