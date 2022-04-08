package com.skilli.game.assignment.games.infrastructure.repository;

import com.skilli.game.assignment.games.infrastructure.repository.entity.GameTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Set;

@Repository
public interface IGameTransactionRepository extends JpaRepository<GameTransaction,Long> {

    @Query("SELECT gameTransaction FROM GameTransaction AS gameTransaction WHERE " +
            "gameTransaction.game.startTime <= :currentTime AND gameTransaction.game.endTime > :currentTime")
    Set<GameTransaction> getCurrentlyActiveGames(@Param("currentTime")Timestamp  currentTimestamp);

    @Query("SELECT gameTransaction FROM GameTransaction AS gameTransaction WHERE gameTransaction.game.startTime >= :currentTime")
    Set<GameTransaction> getUpcomingGames(@Param("currentTime")Timestamp  currentTimestamp);
}
