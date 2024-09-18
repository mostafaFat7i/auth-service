package com.safalifter.authservice.service.imp;



import com.safalifter.authservice.config.CustomUserDetails;
import com.safalifter.authservice.dto.AuthRequest;
import com.safalifter.authservice.dto.SuccessResponseDto;
import com.safalifter.authservice.dto.UserDto;
import com.safalifter.authservice.entities.User;
import com.safalifter.authservice.repo.UserRepo;
import com.safalifter.authservice.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImp implements UserServices {


    private static final Logger infoLogger = LoggerFactory.getLogger("infoLogger");

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean saveUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            infoLogger.info("New user added with id: " + user.getId());
            return true;
        } catch (Exception e) {
            infoLogger.error(e.getMessage());
            return false;
        }

//        return "user added to the system";
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    @Override
    public String validateToken(String token) {
        return jwtService.validateToken(token);
    }

    @Override
    public void invalidateToken(String token) {
        jwtService.invalidateToken(token);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.getReferenceById(id);
    }

    @Override
    public ResponseEntity<Object> checkAuthentacation(Authentication authenticate, AuthRequest authRequest) {
        SuccessResponseDto successResponseDto = new SuccessResponseDto();

        successResponseDto.setToken(generateToken(authRequest.getEmail()));
        successResponseDto.setPermissions(new ArrayList<>());
        UserDto userDto = new UserDto();
        CustomUserDetails userDetails=authenticate.getPrincipal() instanceof CustomUserDetails ? (CustomUserDetails) authenticate.getPrincipal() : null;
        User user=userRepo.findById(userDetails.getId()).get();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        successResponseDto.setUser(userDto);
        Map<String,SuccessResponseDto> map = new HashMap<>();
        map.put("data",successResponseDto);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

}

