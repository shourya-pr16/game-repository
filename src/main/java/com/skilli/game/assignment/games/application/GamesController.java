package com.skilli.game.assignment.games.application;

import com.skilli.game.assignment.firebase.domain.service.IFirebaseDataRetriever;
import com.skilli.game.assignment.games.application.mapper.GameMapper;
import com.skilli.game.assignment.games.application.response.ActiveGame;
import com.skilli.game.assignment.games.application.response.ActiveGamesResponse;
import com.skilli.game.assignment.games.domain.service.IGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class GamesController {

    @Autowired
    private IGameService gameService;
    @Autowired
    private IFirebaseDataRetriever firebaseDataRetriever;

    @Operation(description = "Retrieves all the game events that are currently running.")
    @GetMapping(path = "/getActiveGames", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameter(name = "Authorization Parameter",description = "Authorization header will be the JWT token issued to the user",
            required = true,example = "bhfbbsfdbyhfu.37432gyfg.dufh7", schema = @Schema)
    public ResponseEntity<ActiveGamesResponse> getActiveGames() {
        log.debug("Fetching available Games");
        GameMapper mapper = Mappers.getMapper(GameMapper.class);
        List<ActiveGame> activeGamesList = mapper.toApiGameModelList(gameService.getActiveGames());
        return ResponseEntity.ok(ActiveGamesResponse.builder().games(activeGamesList).build());
    }

    @Operation(description = "Retrieves information about the games that will be active in future.")
    @GetMapping(path = "/getUpcomingGames", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameter(name = "Authorization Parameter",description = "Authorization header will be the JWT token issued to the user",
            required = true,example = "bhfbbsfdbyhfu.37432gyfg.dufh7", schema = @Schema)
    public ResponseEntity<ActiveGamesResponse> getUpcomingGames() {
        log.debug("Fetching upcoming games");
        GameMapper gameMapper = Mappers.getMapper(GameMapper.class);
        List<ActiveGame> activeGames = gameMapper.toApiGameModelList(gameService.getUpcomingGames());
        return ResponseEntity.ok(ActiveGamesResponse.builder().games(activeGames).build());
    }

    @Operation(description = "Open this api in browser to download the game image.")
    @GetMapping(path = "/getGameImage")
    public ResponseEntity<ByteArrayResource> getGameImage(@RequestParam("imageLocation") String imageLocation) throws Exception {
        ByteArrayResource imageResource = firebaseDataRetriever.fetchImage(imageLocation);
        String fileName = imageLocation.split("/")[1];
        return ResponseEntity.ok()
                .contentLength(imageResource.contentLength())
                .header("Content-type", "application/octet-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(imageResource);
    }

}
