package com.skilli.game.assignment.games.infrastructure.repository;

import com.skilli.game.assignment.games.infrastructure.repository.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends JpaRepository<Game,Long> {
}
