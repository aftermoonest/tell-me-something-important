package com.aftermoonest.tell_me_something_important.repository;

import com.aftermoonest.tell_me_something_important.components.Item;
import com.vaadin.flow.component.textfield.EmailField;

public interface Controller {
    void save(Object item, String key);

    boolean isInDatabase(String key);

    boolean isTextCorrect(String value);

    boolean isEmailCorrect(EmailField emailField);

    Item buildItem(String email, String name, String date, String text);
}
