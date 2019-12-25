package com.aftermoonest.tell_me_something_important.repository;

public class Incrementer {
    private static int incrementer = 0;

    public static int getIncrementer() {
        incrementer++;
        return (incrementer - 1);
    }
}
