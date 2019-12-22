package com.aftermoonest.tell_me_something_important.component;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ClickLabel extends VerticalLayout {

    private final Label label;

    public ClickLabel(String value) {
        setWidth("15%");
        label = new Label(value);
        add(label);
    }

    private void animation(){

    }
}
