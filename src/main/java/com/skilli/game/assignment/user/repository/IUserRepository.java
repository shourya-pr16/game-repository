package com.skilli.game.assignment.user.repository;

import com.skilli.game.assignment.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User as user where user.username=:userName")
    User getByUserName(@Param("userName") String userName);

    @Query("SELECT user.username FROM User as user")
    Set<String> getAllAvailableUserNames();

}
