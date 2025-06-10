package com.taxi.auth_service.repository;

import com.taxi.auth_service.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT s FROM User s WHERE s.username=:username")
    Optional<User> findByUsername(String username);
}
