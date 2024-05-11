package com.example.butcher_shop.repositories;


import com.example.butcher_shop.databases.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    User findUserById(Long id);
}
