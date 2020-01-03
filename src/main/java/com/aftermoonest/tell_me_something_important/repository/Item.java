package com.aftermoonest.tell_me_something_important.repository;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {
    
    Integer key;
    String name;
    String date;
    String text;

    public Item() {

    }

    public Item(String name, Integer key, String date, String text) {
        this.key = key;
        this.name = name;
        this.date = date;
        this.text = text;
    }
}
