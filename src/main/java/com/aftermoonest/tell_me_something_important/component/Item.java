package com.aftermoonest.tell_me_something_important.component;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate date;
    private String text;

    public Item(String name, LocalDate date, String text) {
        this.name = name;
        this.date = date;
        this.text = text;
    }
}
