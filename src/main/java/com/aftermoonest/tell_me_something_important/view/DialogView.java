package com.aftermoonest.tell_me_something_important.view;

import com.aftermoonest.tell_me_something_important.strings.Values;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

class DialogView extends Dialog {

    private final Button closeButton = new Button(Values.close, VaadinIcon.CLOSE.create());
    private final VerticalLayout buttonLayout = new VerticalLayout();

    @Autowired
    DialogView(String value) {
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);

        setButtonListener();

        buttonLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        buttonLayout.add(closeButton);

        add(new Label(value), buttonLayout);
    }

    private void setButtonListener(){
        closeButton.addClickListener(e -> close());
    }
}
