package com.easybuy.user_details_service.repository;

import com.easybuy.user_details_service.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.username=:username")
    public Optional<User> getUserByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.username = :username")
    int updatePasswordByUsername(@Param(value = "password") String password,
                                 @Param(value = "username") String username);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newPassword, u.username=:newUsername WHERE u.username = :username")
    int updateUsernameAndPasswordByUsername(@Param(value = "newPassword") String newPassword,
                                             @Param(value = "newUsername") String newUsername,
                                             @Param(value = "username")String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.username = :username AND u.emailId = :emailId")
    int deleteUserByUsernameAndEmailId(@Param("username") String username, @Param("emailId") String emailId);
}
