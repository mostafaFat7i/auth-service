package com.safalifter.authservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
