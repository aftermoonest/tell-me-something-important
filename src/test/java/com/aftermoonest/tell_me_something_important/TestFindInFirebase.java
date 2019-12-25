package com.aftermoonest.tell_me_something_important;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class TestFindInFirebase {

    private String url = "https://testbd-6299b.firebaseio.com/";
    private String path = "/serviceAccount_test.json";

    private FirebaseDatabase database;
    private DatabaseReference ref;


    @Test
    public void testFind(){
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

        String key = Integer.toString("kovalskoi.alex@gmail.com".hashCode());
        System.out.println("Key: " + key);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference(key);

        String value = ref.push().getKey();
        System.out.println(value);
    }
}