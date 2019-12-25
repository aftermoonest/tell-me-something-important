package com.aftermoonest.tell_me_something_important;

import com.aftermoonest.tell_me_something_important.component.Item;
import com.aftermoonest.tell_me_something_important.repository.Incrementer;
import org.junit.Test;

import java.time.LocalDate;

public class TestIncrement {
    @Test
    public void testIncrement(){
        Item item = Item.builder()
                .id(Incrementer.getIncrementer())
                .name("Alexey")
                .date(LocalDate.now())
                .text("Lorem")
                .build();

        Item item2 = Item.builder()
                .id(Incrementer.getIncrementer())
                .name("Andrey")
                .date(LocalDate.now())
                .text("Lorem")
                .build();

        Item item3 = Item.builder()
                .id(Incrementer.getIncrementer())
                .name("Max")
                .date(LocalDate.now())
                .text("Lorem")
                .build();

        System.out.println(item.toString());
        System.out.println(item2.toString());
        System.out.println(item3.toString());
    }
}
