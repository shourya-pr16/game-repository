package com.skilli.game.assignment.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Java based configuration file to connect to Firebase App
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Game Competition Application",description = "Use this app for registering and playing games"))
public class FirebaseConfig {

    @Value("${firebase.bucket.name}")
    private String firebaseBucketName;
    @Value("${firebase.credentials.location}")
    private String firebaseCredentialFilePath;

    /**
     * Connects with firebase app and makes a bean
     * of that Firebase app
     *
     * @return the firebase app
     * @throws IOException the io exception
     */
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(firebaseCredentialFilePath);

        FirebaseOptions options = FirebaseOptions.builder().setStorageBucket(firebaseBucketName)
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        return firebaseApp;
    }

    /**
     * Firebase storage instance
     *
     * @return the storage
     * @throws IOException the io exception
     */
    @Bean
    public Storage storage() throws IOException {
        Storage storage = StorageClient.getInstance(firebaseApp()).bucket().getStorage();
        return storage;
    }

    /**
     * Firebase Remote config instance
     *
     * @return the firebase remote config
     * @throws IOException the io exception
     */
    @Bean
    public FirebaseRemoteConfig remoteConfig() throws IOException {
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance(firebaseApp());
        return firebaseRemoteConfig;
    }

    /**
     * Cloud firebase instance.
     *
     * @return the firestore
     * @throws IOException the io exception
     */
    @Bean
    public Firestore cloudFirebase() throws IOException {
        Firestore firestore = FirestoreClient.getFirestore(firebaseApp());
        return firestore;
    }

}
