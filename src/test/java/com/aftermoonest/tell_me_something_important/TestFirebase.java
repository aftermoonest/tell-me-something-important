package com.aftermoonest.tell_me_something_important;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import lombok.Data;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.GeneratedValue;
import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;

public class TestFirebase {

    private String url = "https://testbd-6299b.firebaseio.com/";
    private String path = "/serviceAccount_test.json";

    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Test
    public void testbd() {
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

        User user = new User("Gordon", LocalDate.now().toString(), "Lorem Ipsum");

        database = FirebaseDatabase.getInstance();

        update(user);
    }

    private void update(Object value) {
        update(value, "testbd-6299b");
    }

    private void update(Object value, String key) {
        try {
            ref = database.getReference().child("user");
            final CountDownLatch latch = new CountDownLatch(1);

            // ref.setValue(value, new DatabaseReference.CompletionListener() {
            ref.setValue(value, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                    latch.countDown();
                } else {
                    System.out.println("Data saved successfully.");
                    latch.countDown();
                }
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Data
    class User {

        @GeneratedValue
        Integer id;
        String name;
        String date;
        String text;

        User(String name, String date, String text) {
            this.name = name;
            this.date = date;
            this.text = text;
        }
    }

}
