package com.skilli.game.assignment.user.domain.service;

import com.skilli.game.assignment.securityconfig.CustomUserDetailsService;
import com.skilli.game.assignment.securityconfig.JwtUtil;
import com.skilli.game.assignment.firebase.domain.service.IFirebaseDataRetriever;
import com.skilli.game.assignment.games.infrastructure.repository.IGameTransactionRepository;
import com.skilli.game.assignment.games.infrastructure.repository.entity.Game;
import com.skilli.game.assignment.games.infrastructure.repository.entity.GameTransaction;
import com.skilli.game.assignment.user.application.api.PlayGameResponse;
import com.skilli.game.assignment.user.domain.service.model.RegisteredGameModel;
import com.skilli.game.assignment.user.repository.IUserRepository;
import com.skilli.game.assignment.user.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class UserService implements IUserService {
    public static final String CURRENT_SERVER_ID_FROM_FIREBASE_PROP = "imageDownloadServerUrl";

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IGameTransactionRepository gameTransactionRepository;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private IFirebaseDataRetriever firebaseDataRetriever;

    @Override
    public String signUpUser(User user) throws UsernameAlreadyPresentException {
        // TODO -> to be cached
        Set<String> usernames = userRepository.getAllAvailableUserNames();
        if (usernames.contains(user.getUsername()))
            throw new UsernameAlreadyPresentException("This username is already present :" + user.getUsername());
        user = userRepository.save(user);
        return "User saved : " + user.getUsername();
    }

    @Override
    public String registerUserForGame(String gameEventId, String jwtToken) {
        try {
            String username = jwtUtil.extractUsername(jwtToken);
            User user = userRepository.getByUserName(username);
            GameTransaction gameTransaction = gameTransactionRepository.findById(Long.parseLong(gameEventId)).get();
            if (gameTransaction.getRegisteredUsers().size() < gameTransaction.getPoolSize()) {
                gameTransaction.getRegisteredUsers().add(user);
                gameTransactionRepository.save(gameTransaction);
                return "Registration Successful for Game : " + gameTransaction.getGame().getTitle();
            }
        } catch (Exception e) {
            log.error("Error while registration for Game : " + ExceptionUtils.getStackTrace(e));
            return "Registration unsuccessful due to Exception : " + e.getMessage();
        }
        return "Registration Unsuccessful : Maximum Registered User limit reached.";
    }

    @Override
    public String login(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username/password", e);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(userDetails);
        return jwtToken;
    }

    @Override
    public List<RegisteredGameModel> getRegisteredGames(String jwtToken) {
        List<RegisteredGameModel> myGames = new ArrayList<>();
        try {
            RegisteredGameModel registeredGameModel;
            Set<GameTransaction> registeredGames = getRegisteredGameTransactions(jwtToken);
            for (GameTransaction gameEvent : registeredGames) {
                registeredGameModel = RegisteredGameModel.builder()
                        .endTime(gameEvent.getGame().getEndTime().toLocalDateTime())
                        .startTime(gameEvent.getGame().getStartTime().toLocalDateTime())
                        .gameEventId(String.valueOf(gameEvent.getTransactionId()))
                        .gameTitle(gameEvent.getGame().getTitle())
                        .gamePlayUrl(firebaseDataRetriever.getGamePlayUrl(gameEvent.getGame().getGamePlayUrl()))
                        .gameImageUrl(prepareImageUrl(gameEvent.getGame()))
                        .build();
                myGames.add(registeredGameModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myGames;
    }

    @Override
    public PlayGameResponse playGame(String gameEventId, String jwtToken) throws Exception {
        String username = jwtUtil.extractUsername(jwtToken);
        Set<GameTransaction> userRegisteredGames = getRegisteredGameTransactions(jwtToken);
        GameTransaction playableGame = userRegisteredGames.stream().filter(s -> s.getTransactionId() == Long.parseLong(gameEventId)).findFirst().orElse(null);
        if (playableGame == null)
            throw new RuntimeException("User " + username + " is not registered to play this game. Kindly register first");
        if (!isStilllActive(playableGame)) throw new RuntimeException("Game has been ended.");
        PlayGameResponse playGameResponse = PlayGameResponse.builder()
                .gameTitle(playableGame.getGame().getTitle())
                .gameUrl(firebaseDataRetriever.getGamePlayUrl(playableGame.getGame().getGamePlayUrl()))
                .build();
        return playGameResponse;
    }

    private boolean isStilllActive(GameTransaction playableGame) {
        if (playableGame.getGame().getStartTime().toLocalDateTime().isBefore(LocalDateTime.now())){
            if(playableGame.getGame().getEndTime().toLocalDateTime().isAfter(LocalDateTime.now())){
                return true;
            }
        }
        return false;
    }

    private URI prepareImageUrl(Game game) throws Exception {
        Map<String, String> propertyValues = firebaseDataRetriever.getPropertyValues(CURRENT_SERVER_ID_FROM_FIREBASE_PROP);
        StringBuilder urlString = new StringBuilder(propertyValues.get(CURRENT_SERVER_ID_FROM_FIREBASE_PROP)).append("getGameImage?imageLocation=").append(game.getImageUrl());
        return URI.create(urlString.toString());
    }

    private Set<GameTransaction> getRegisteredGameTransactions(String jwtToken) {
        String username = jwtUtil.extractUsername(jwtToken);
        User user = userRepository.getByUserName(username);
        Set<GameTransaction> registeredGames = user.getRegisteredGames();
        return registeredGames;
    }
}
