package com.aftermoonest.tell_me_something_important.components;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ClickLabel extends VerticalLayout {

    public ClickLabel(String value) {
        setWidth("10%");
        add(new Label(value));
    }
}
