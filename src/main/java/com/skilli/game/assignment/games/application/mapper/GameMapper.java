package com.skilli.game.assignment.games.application.mapper;

import com.skilli.game.assignment.games.application.response.ActiveGame;
import com.skilli.game.assignment.games.domain.service.model.GameModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {

    @Mapping(source = "gameEventId", target = "gameEventId")
    @Mapping(source = "gameTitle", target = "gameTitle")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    @Mapping(source = "currentlyEnrolledPlayers", target = "currentlyEnrolledPlayers")
    ActiveGame toApiGameModel(GameModel gameModel);

    default List<ActiveGame> toApiGameModelList(List<GameModel> gameModels) {
        List<com.skilli.game.assignment.games.application.response.ActiveGame> activeGameListApi = gameModels.stream().map(s -> toApiGameModel(s)).collect(Collectors.toList());
        return activeGameListApi;
    }
}
