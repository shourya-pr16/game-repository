package com.skilli.game.assignment.firebase.domain.service;

import com.google.cloud.ReadChannel;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.firebase.remoteconfig.*;
import com.skilli.game.assignment.firebase.domain.service.model.GameLocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class FirebaseDataRetriever implements IFirebaseDataRetriever {
    public static final String REMOTE_CONFIG_GAME_PROPERTIES_COLLECTION_NAME = "game-app-properties";
    public static final String FIREBASE_GAME_URL_PARENT_DOC_NAME = "game-play-urls";

    @Autowired
    private Storage storage;
    @Autowired
    private FirebaseRemoteConfig firebaseRemoteConfig;
    @Value("${firebase.bucket.name}")
    private String firebaseBucketName;
    @Autowired
    private Firestore firestore;

    @Override
    public ByteArrayResource fetchImage(String imageName) throws Exception {
        try{
            BlobId imageId = BlobId.of(firebaseBucketName, imageName);
            Blob imageBlob = storage.get(imageId);
            ReadChannel reader = imageBlob.reader();
            InputStream inputStream = Channels.newInputStream(reader);
            byte[] content = IOUtils.toByteArray(inputStream);
            return new ByteArrayResource(content);
        }catch (Exception e){
            log.error("Error while fetching the image file : "+ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public Map<String, String> getPropertyValues(String... propertyNames) throws Exception {
        Map<String, String> parameterValues;
        AtomicReference<ParameterValue.Explicit> parameterValue = new AtomicReference<>();
        try {
            Template latestTemplate = firebaseRemoteConfig.getTemplate();
            parameterValues = new HashMap<>();
            Map<String, ParameterGroup> parameters = latestTemplate.getParameterGroups();
            Arrays.stream(propertyNames).forEach(propertyName -> {
                parameterValue.set((ParameterValue.Explicit) parameters.get(REMOTE_CONFIG_GAME_PROPERTIES_COLLECTION_NAME)
                        .getParameters().get(propertyName).getDefaultValue());
                parameterValues.put(propertyName, parameterValue.get().getValue());
            });
        } catch (FirebaseRemoteConfigException e) {
            log.error("Error occurred while fetching config template from firebase : " + ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
        return parameterValues;
    }

    @Override
    public String getGamePlayUrl(String gameDocumentId) throws Exception {
        CollectionReference collectionReference = firestore.collection(FIREBASE_GAME_URL_PARENT_DOC_NAME);
        try {
            DocumentSnapshot documentSnapshot = collectionReference.document(gameDocumentId).get().get();
            GameLocation gameLocation = documentSnapshot.toObject(GameLocation.class);
            return gameLocation.getUrl();
        } catch (InterruptedException | ExecutionException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }
}
