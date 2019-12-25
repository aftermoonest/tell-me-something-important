package com.aftermoonest.tell_me_something_important.repository;

import com.aftermoonest.tell_me_something_important.component.Item;

public interface Database {
    void save(Item item);
}
