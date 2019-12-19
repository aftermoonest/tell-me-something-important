package com.aftermoonest.tell_me_something_important;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("frontend://styles/styles-item.css")
public class ItemView extends VerticalLayout {

    private Label name;
    private Label text;

    public ItemView() {
        setClassName("item-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setWidth("75%");
        name = new Label("Name of user");
        text = new Label("Lorem ipsum dolor " +
                "sit amet, consectetur adipiscing " +
                "elit, sed do eiusmod tempor incididunt " +
                "ut labore et dolore magna aliqua. Ut " +
                "enim ad minim veniam, quis nostrud " +
                "exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis " +
                "aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum dolore eu " +
                "fugiat nulla pariatur. Excepteur sint " +
                "occaecat cupidatat non proident, sunt " +
                "in culpa qui officia deserunt mollit " +
                "anim id est laborum.\n");

        add(name, text);
    }
}
