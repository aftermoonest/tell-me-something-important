package com.aftermoonest.tell_me_something_important.repository;

import com.vaadin.flow.component.textfield.EmailField;

import java.util.List;

public interface Controller {
    Integer countOfElementsOnPage = 10;

    void save(Object item, String key);

    List<Item> get();

    boolean find(String key);

    boolean isTextCorrect(String value);

    boolean isEmailCorrect(EmailField emailField);

    Item buildItem(String email, String name, String date, String text);

    Integer defineCountOfPages();

    Integer getCountOfElementsOnPage();
}
