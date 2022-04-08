package com.skilli.game.assignment.user.application;

import com.skilli.game.assignment.MessageResponse;
import com.skilli.game.assignment.user.application.api.AuthenticationRequest;
import com.skilli.game.assignment.user.application.api.AuthenticationResponse;
import com.skilli.game.assignment.user.application.api.PlayGameResponse;
import com.skilli.game.assignment.user.application.api.SignUpRequest;
import com.skilli.game.assignment.user.application.mapper.UserMapper;
import com.skilli.game.assignment.user.domain.service.IUserService;
import com.skilli.game.assignment.user.domain.service.UsernameAlreadyPresentException;
import com.skilli.game.assignment.user.domain.service.model.RegisteredGameModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(description = "Use this api to authenticate your identity. Use your username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication Successful", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthenticationResponse.class))
            }),
            @ApiResponse(responseCode = "403", description = "Authentication Failed")
    })
    @Parameters(value = {@Parameter(name = "Username",
            description = "Username of the registered user.",
            schema = @Schema(implementation = String.class)),
            @Parameter(name = "Password",
                    description = "Password of the registered user.",
                    schema = @Schema(implementation = String.class))})
    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> getAuthenticated(@RequestBody AuthenticationRequest authRequest) throws Exception {
        String jwtToken = userService.login(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken(jwtToken).build());
    }

    @Operation(description = "Use this api to sign up as new user, provided username is unique.")
    @ApiResponses(value = {@ApiResponse(description = "Successful Response", responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(description = "Registration was not successful",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponse.class)))})
    @Parameters(value = {
            @Parameter(name = "Username", schema = @Schema),
            @Parameter(name = "firstName", schema = @Schema),
            @Parameter(name = "lastName", schema = @Schema),
            @Parameter(name = "password", schema = @Schema),
            @Parameter(name = "emailId", schema = @Schema),
    })
    @PostMapping(path = "/signUp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> signUp(@RequestBody SignUpRequest request) throws UsernameAlreadyPresentException {
        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        String userResponse = userService.signUpUser(mapper.toUserEntity(request));
        return ResponseEntity.ok(MessageResponse.builder().message(userResponse).build());
    }

    @Operation(description = "Registers logged in user for provided game event.")
    @GetMapping(path = "/user/registration/{gameEventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> registerForGame(@PathVariable String gameEventId, @RequestHeader("Authorization") String authorizationJwtToken) {
        String registrationMessage = userService.registerUserForGame(gameEventId, authorizationJwtToken);
        return ResponseEntity.ok(MessageResponse.builder().message(registrationMessage).build());
    }

    @Operation(description = "Retrieves all the games in which logged in user is registered.")
    @GetMapping(path = "/user/getMyRegisteredGames", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegisteredGameModel>> registeredGames(@RequestHeader("Authorization") String jwtToken) {
        List<RegisteredGameModel> registeredGames = userService.getRegisteredGames(jwtToken);
        return ResponseEntity.ok(registeredGames);
    }

    @Operation(description = "Use this api to fetch game url and play game.")
    @GetMapping(path = "/user/playGame/{gameEventId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayGameResponse> playGame(@PathVariable String gameEventId,
                                                     @RequestHeader("Authorization") String jwtToken) throws Exception {
        log.debug("Ready to play game : "+gameEventId);
        PlayGameResponse gameResponse = userService.playGame(gameEventId, jwtToken);
        return ResponseEntity.ok(gameResponse);
    }

}
