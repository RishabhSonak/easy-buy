package com.easybuy.user_details_service.repository;

import com.easybuy.user_details_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.username=:username")
    public Optional<User> getUserByUsername(@Param("username") String username);

}

// TODO: 28-06-2024  create 2 new repos, add:  