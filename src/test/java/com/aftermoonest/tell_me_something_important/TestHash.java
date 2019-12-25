package com.aftermoonest.tell_me_something_important;

import org.junit.Test;

public class TestHash {
    @Test
    public void testHash(){
        String someString = "Lorem ipsum";
        String someString2 = "Lorem ipsum dolor";
        String someString3 = "Lorem ipsum sir";
        String someString4 = "Lorem ipsum amet";
        String someString5 = "Lorem ipsum doloto";

        System.out.println(someString.hashCode());
        System.out.println(someString2.hashCode());
        System.out.println(someString3.hashCode());
        System.out.println(someString4.hashCode());
        System.out.println(someString5.hashCode());
    }
}
