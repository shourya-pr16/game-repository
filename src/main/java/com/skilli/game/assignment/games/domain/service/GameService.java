package com.skilli.game.assignment.games.domain.service;

import com.skilli.game.assignment.games.domain.service.model.GameModel;
import com.skilli.game.assignment.games.infrastructure.repository.IGameTransactionRepository;
import com.skilli.game.assignment.games.infrastructure.repository.entity.Game;
import com.skilli.game.assignment.games.infrastructure.repository.entity.GameTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GameService implements IGameService {

    @Autowired
    private IGameService gameRepository;
    @Autowired
    private IGameTransactionRepository gameTransactionRepository;

    @Override
    public List<GameModel> getActiveGames() {
        LocalDateTime currentTime = LocalDateTime.now().minusMinutes(5);
        Set<GameTransaction> currentGames = gameTransactionRepository.getCurrentlyActiveGames(Timestamp.valueOf(currentTime));
        List<GameModel> gamesListModel = getGameModelsFromGameTransactions(currentGames);
        return gamesListModel;
    }

    @Override
    public List<GameModel> getUpcomingGames() {
        Set<GameTransaction> upcomingGameTransactions = gameTransactionRepository.getUpcomingGames(Timestamp.valueOf(LocalDateTime.now()));
        List<GameModel> games = this.getGameModelsFromGameTransactions(upcomingGameTransactions);
        return games;
    }

    private List<GameModel> getGameModelsFromGameTransactions(Set<GameTransaction> currentGames) {
        List<GameModel> gamesListModel = new ArrayList<>();
        GameModel gameModel = null;
        Game game;
        for (GameTransaction gameTransaction : currentGames) {
            game = gameTransaction.getGame();
            gameModel = GameModel.builder()
                    .gameEventId(String.valueOf(gameTransaction.getTransactionId()))
                    .currentlyEnrolledPlayers(gameTransaction.getRegisteredUsers().size())
                    .endTime(game.getEndTime().toLocalDateTime())
                    .startTime(game.getStartTime().toLocalDateTime())
                    .gameTitle(game.getTitle())
                    .build();
            gamesListModel.add(gameModel);
        }
        return gamesListModel;
    }
}
