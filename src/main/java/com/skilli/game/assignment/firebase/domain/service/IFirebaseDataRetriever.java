package com.skilli.game.assignment.firebase.domain.service;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.util.Map;

/**
 * This interface deals with the transactions related to the Firebase services
 * i.e. Firebase Remote Config, Cloud Firestore, Firebase Storage
 */
public interface IFirebaseDataRetriever {
    /**
     * Retrieves content data for the game images
     * stored in Firebase Storage
     *
     * @param imageName the image name
     * @return the byte array resource
     * @throws IOException the io exception
     * @throws Exception   the exception
     */
    ByteArrayResource fetchImage(String imageName) throws IOException, Exception;

    /**
     * Retrieves propertyValues from the properties hosted
     * in Firebase Remote Config
     *
     * @param propertyName the property name
     * @return the property values
     * @throws Exception the exception
     */
    Map<String,String> getPropertyValues(String... propertyName) throws Exception;

    /**
     * Retrieves the game play urls stored in Cloud Firestore
     *
     * @param gameDocumentId the game document id
     * @return the game play url
     * @throws Exception the exception
     */
    String getGamePlayUrl(String gameDocumentId) throws Exception;
}
