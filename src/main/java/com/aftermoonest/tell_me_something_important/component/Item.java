package com.aftermoonest.tell_me_something_important.component;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder()
public class Item {

    Integer id;
    String name;
    LocalDate date;
    String text;

    /*public Item(String name, LocalDate date, String text) {
        this.name = name;
        this.date = date;
        this.text = text;

    }*/
}
