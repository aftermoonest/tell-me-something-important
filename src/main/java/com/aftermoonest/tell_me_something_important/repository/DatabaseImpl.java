package com.aftermoonest.tell_me_something_important.repository;

import com.aftermoonest.tell_me_something_important.component.Item;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class DatabaseImpl implements Database {

    private String url = "https://testbd-6299b.firebaseio.com/";
    private String path = "/serviceAccount_test.json";

    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    public void save(Item item) {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource(path).getInputStream()))
                    .setDatabaseUrl(url).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                database = FirebaseDatabase.getInstance();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        update(item, item.getId().toString());
    }

    private void update(Object value, String key) {
        try {
            ref = database.getReference().child(key);
            final CountDownLatch latch = new CountDownLatch(1);

            //ref.setValue(value, new DatabaseReference.CompletionListener() {
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
}
