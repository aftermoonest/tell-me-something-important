package com.aftermoonest.tell_me_something_important.components;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder()
public class Item {
    String email;
    String name;
    String date;
    String text;
}
