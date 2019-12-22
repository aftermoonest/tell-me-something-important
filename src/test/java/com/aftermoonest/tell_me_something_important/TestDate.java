package com.aftermoonest.tell_me_something_important;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDate {

    @Test
    public void tryDate(){
        LocalDate date = LocalDate.now();
        System.out.println(date);
        String dateS = date.toString();
        System.out.println(dateS);
    }
}
