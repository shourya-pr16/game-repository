package com.skilli.game.assignment.user.domain.service;

import com.skilli.game.assignment.user.application.api.PlayGameResponse;
import com.skilli.game.assignment.user.domain.service.model.RegisteredGameModel;
import com.skilli.game.assignment.user.repository.entity.User;

import java.util.List;

/**
 * This interface deals with all the user related crud operations and also registers users into the game
 */
public interface IUserService {

    /**
     * This method is used to signUp a new user in the database.
     *
     * @param user the user
     * @return the string
     * @throws UsernameAlreadyPresentException the username already present exception
     */
    String signUpUser(User user) throws UsernameAlreadyPresentException;

    /**
     * Register the logged in user into the game event.
     *
     * @param gameEventId the game event id
     * @param jwtToken    the jwt token
     * @return the string
     */
    String registerUserForGame(String gameEventId, String jwtToken);

    /**
     * This method helps user to login into the app and get a JWT token
     * which can be further used to access other endpoints
     *
     * @param userName the user name
     * @param password the password
     * @return the string
     * @throws Exception the exception
     */
    String login(String userName, String password) throws Exception;

    /**
     * Gets registered games for the logged in user.
     *
     * @param jwtToken the jwt token
     * @return the registered games
     */
    List<RegisteredGameModel> getRegisteredGames(String jwtToken);

    /**
     *Lets the user play into anyone of his/her registered games.
     *
     * @param gameEventId the game event id
     * @param jwtToken    the jwt token
     * @return the play game response
     * @throws Exception the exception
     */
    PlayGameResponse playGame(String gameEventId, String jwtToken) throws Exception;
}
