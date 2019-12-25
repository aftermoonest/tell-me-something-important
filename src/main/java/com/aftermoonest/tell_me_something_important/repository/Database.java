package com.aftermoonest.tell_me_something_important.repository;

import com.google.firebase.database.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.CountDownLatch;

@FieldDefaults(level = AccessLevel.PUBLIC)
class Database {

    private static FirebaseDatabase database;
    private static DatabaseReference ref;

    static void save(Object item, String key) {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child(key);

        try {
            final CountDownLatch latch = new CountDownLatch(1);

            //ref.setValue(value, new DatabaseReference.CompletionListener() {
            ref.setValue(item, (databaseError, databaseReference) -> {
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

    private static boolean isInDatabase;

    static boolean findByKey(String key){
        // TODO : заглушка
        isInDatabase = false;
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        Query query = ref.equalTo(key);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    if (post.hasChild(key)) {
                        isInDatabase = true;
                    }
                }
                isInDatabase = false;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return isInDatabase;
    }


}
