package com.aftermoonest.tell_me_something_important.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;


@FieldDefaults(level = AccessLevel.PUBLIC)
class Database {

    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    private static Firestore database;

    private static String collection = "users";

    static void save(Object item, Integer id) {
        String key = Integer.toString(id);

        database = FirestoreClient.getFirestore();
        logger.info("database initialized");

        DocumentReference documentReference = database.collection(collection).document(key);
        logger.info("document reference initialized: collection - " + collection);

        ApiFuture<WriteResult> result = documentReference.set(item);
        try {
            logger.info("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            logger.error("InterruptedException: " + e.getMessage());
        } catch (ExecutionException e) {
            logger.error("ExecutionException: " + e.getMessage());
        }
    }

    static List<Item> get() {
        database = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = database.collection(collection).get();
        QuerySnapshot querySnapshot = null;

        try {
            querySnapshot = query.get();
            logger.info("Update time : " + query.get().getReadTime());
        } catch (InterruptedException e) {
            logger.error("InterruptedException: " + e.getMessage());
        } catch (ExecutionException e) {
            logger.error("ExecutionException: " + e.getMessage());
        }

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        Item item;
        List<Item> items = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            item = new Item();

            item.setId(Integer.parseInt(document.getId()));
            item.setDate(document.getString("date"));
            item.setName(document.getString("name"));
            item.setText(document.getString("text"));
            logger.info(item.toString());

            items.add(item);
        }

        System.out.println(Arrays.toString(items.toArray()));
        return items;
    }

    static boolean find(Integer key) {
        List<Item> items = get();
        for (Item item : items) {
            if (item.getId().equals(key)) {
                return true;
            }
        }
        return false;
    }
}
