package com.aftermoonest.tell_me_something_important;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    //TODO : change url and service account of database from test to production.
    private static final String URL = "https://testbd-6299b.firebaseio.com/";
    private static final String PATH = "/serviceAccount_test.json";


    public static void main(String[] args) {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource(PATH).getInputStream()))
                    .setDatabaseUrl(URL).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpringApplication.run(Application.class, args);
    }

    // TODO : зробити пагінацію (Катя каже, шо це переключення
    //  між сторінками (типу розбиття на сторінки)).

    // TODO : починити фронтенд (вилазять блоки за межі в'ю скріна)

    // TODO : зробити пошук (Катя каже, шо так нада і точка).

    // TODO : зробити фільтрацію по даті додання.

    // TODO : зробити сортування.

    // TODO : зробити можливість редагування в періоді
    //  одного дня та не більше одного разу на користувача.

    // TODO : покрити всю програму тестами.

    // TODO : написати джавадоки до всьої програми.

    // TODO : добавити логування.
}
