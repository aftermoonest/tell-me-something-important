package com.aftermoonest.tell_me_something_important.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


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
        QuerySnapshot querySnapshot;
        List<QueryDocumentSnapshot> documents = null;

        try {
            querySnapshot = query.get();
            documents = querySnapshot.getDocuments();

            logger.info("Update time : " + query.get().getReadTime());
        } catch (InterruptedException e) {
            logger.error("InterruptedException: " + e.getMessage());
        } catch (ExecutionException e) {
            logger.error("ExecutionException: " + e.getMessage());
        }

        Item item;
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < documents.size(); i++) {
            QueryDocumentSnapshot document = documents.get(i);
            item = new Item();

            item.setKey(Integer.parseInt(document.getId()));
            item.setDate(document.getString("date"));
            item.setName(document.getString("name"));
            item.setText(document.getString("text"));

            items.add(item);
        }
        Collections.reverse(items);
        return items;
    }

    static boolean find(Integer key) {
        List<Item> items = get();
        for (Item item : items) {
            if (item.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
}
