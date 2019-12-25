package com.aftermoonest.tell_me_something_important.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@StyleSheet("frontend://styles/styles-footer.css")
class FooterView extends Footer {

    private HorizontalLayout layout = new HorizontalLayout();

    FooterView() {
        setClassName("footer-view");

        Button button = new Button();
        Button button2 = new Button();
        Button button3 = new Button();

        layout.add(button, button2, button3);
        add(layout);
    }
}
