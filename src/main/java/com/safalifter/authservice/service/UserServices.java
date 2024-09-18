package com.safalifter.authservice.service;



import com.safalifter.authservice.dto.AuthRequest;
import com.safalifter.authservice.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserServices {

    User getUserById(Long id);

    ResponseEntity<Object> checkAuthentacation(Authentication authenticate, AuthRequest authRequest);

    List<User> findAllUsers();

    String generateToken(String email);

    String validateToken(String token);

    void invalidateToken(String token);

    public boolean saveUser(User user);
}
