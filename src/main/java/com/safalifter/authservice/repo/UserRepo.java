package com.safalifter.authservice.repo;


import com.safalifter.authservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByNameIgnoreCase(String username);
    Optional<User> findByEmail(String email);

}
