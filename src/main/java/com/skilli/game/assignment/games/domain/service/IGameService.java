package com.skilli.game.assignment.games.domain.service;

import com.skilli.game.assignment.games.domain.service.model.GameModel;

import java.util.List;

public interface IGameService {
    List<GameModel> getActiveGames();
    List<GameModel> getUpcomingGames();
}
