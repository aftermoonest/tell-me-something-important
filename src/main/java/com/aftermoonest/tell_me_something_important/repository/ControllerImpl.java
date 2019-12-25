package com.aftermoonest.tell_me_something_important.repository;

import com.aftermoonest.tell_me_something_important.components.Item;
import com.vaadin.flow.component.textfield.EmailField;

public class ControllerImpl implements Controller {

    @Override
    public void save(Object item, String key) {
        String hashKey = Integer.toString(key.hashCode());
        Database.save(item, hashKey);
    }

    @Override
    public boolean isInDatabase(String key) {
        //TODO : need to write method
        return Database.findByKey(key);
    }

    @Override
    public boolean isTextCorrect(String value) {
        if (value.trim().equals("") || value.trim().equalsIgnoreCase("\n")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isEmailCorrect(EmailField emailField) {
        if (emailField.isInvalid() || emailField.getValue().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Item buildItem(String email, String name, String date, String text) {
        return Item.builder()
                .name(name.trim())
                .email(email)
                .text(text.trim())
                .date(date)
                .build();
    }
}
