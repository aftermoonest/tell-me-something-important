package com.aftermoonest.tell_me_something_important.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ClickIcon extends VerticalLayout {

    private Image image;

    public ClickIcon() {
        setWidth("10%");
        image = new Image();
        add(image);
    }
}
