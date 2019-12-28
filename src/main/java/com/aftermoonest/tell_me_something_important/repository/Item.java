package com.aftermoonest.tell_me_something_important.repository;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {
    Integer id;
    String name;
    String date;
    String text;

    public Item() {

    }

    public Item(Integer id, String name, String date, String text) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.text = text;
    }
}
