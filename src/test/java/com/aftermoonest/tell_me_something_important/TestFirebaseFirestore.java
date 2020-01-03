package com.aftermoonest.tell_me_something_important;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Data;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestFirebaseFirestore {

    private String url = "https://testbd-6299b.firebaseio.com/";
    private String path = "/serviceAccount_test.json";

    private Firestore database;
    private DocumentReference documentReference;

    @Test
    public void init() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource(path).getInputStream()))
                    .setDatabaseUrl(url).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        database = FirestoreClient.getFirestore();
    }

    @Test
    public void addData() throws ExecutionException, InterruptedException {
        init();

        User user = new User("max@aftermoonest.com".hashCode(), "Max", LocalDate.now().toString(), "Lorem ipsum");

        String idDocument = Integer.toString(user.getId());

        documentReference = database.collection("users").document(idDocument);
        ApiFuture<WriteResult> result = documentReference.set(user);

        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    @Test
    public void readData() throws ExecutionException, InterruptedException {
        init();

        ApiFuture<QuerySnapshot> query = database.collection("users").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        List<User> users = new ArrayList<>();
        User user = new User();
        for (QueryDocumentSnapshot document : documents) {
            String value = document.getId();
            user.setId(Integer.parseInt(value));

            value = document.getString("date");
            user.setDate(value);

            value = document.getString("name");
            user.setName(value);

            value = document.getString("text");
            user.setText(value);

            users.add(user);
            System.out.println(user.toString());
        }
    }

    @Data
    @Entity
    static class User {
        Integer id;
        String name;
        String date;
        String text;

        User(Integer id, String name, String date, String text) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.text = text;
        }

        User() {

        }
    }
}
