package com.aftermoonest.tell_me_something_important.repository;

import com.vaadin.flow.component.textfield.EmailField;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ControllerImpl implements Controller {

    @Override
    public void save(Object item, String key) {
        Integer hashKey = key.hashCode();
        Database.save(item, hashKey);
    }

    @Override
    public List<Item> get() {
        return Database.get();
    }

    @Override
    public boolean find(String email) {
        Integer hashKey = email.hashCode();
        return Database.find(hashKey);
    }

    @Override
    public boolean isTextCorrect(String value) {
        return !StringUtils.isBlank(value) && !value.trim().equalsIgnoreCase("\n");
    }

    @Override
    public boolean isEmailCorrect(EmailField emailField) {
        return !emailField.isInvalid() && !StringUtils.isBlank(emailField.getValue());
    }

    @Override
    public Item buildItem(String email, String name, String date, String text) {
        return new Item(email.hashCode(), name, date, text);
    }
}
