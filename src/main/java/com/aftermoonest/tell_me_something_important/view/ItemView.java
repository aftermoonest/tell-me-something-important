package com.aftermoonest.tell_me_something_important.view;

import com.aftermoonest.tell_me_something_important.components.Item;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@StyleSheet("frontend://styles/styles-item.css")
public class ItemView extends VerticalLayout {

    private H6 name;
    private H6 date;
    private HorizontalLayout nameLayout;
    private VerticalLayout textLayout;
    private Label text;

    @Autowired
    public ItemView(Item item) {
        initializeItem(item);
    }

    private void initializeItem(Item item){
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        name = new H6(item.getName());
        date = new H6(item.getDate().toString());

        nameLayout = new HorizontalLayout();
        nameLayout.add(name, date);
        textLayout = new VerticalLayout();

        text = new Label(item.getText());
        textLayout.add(text);

        setStyle();

        add(nameLayout, textLayout);
    }

    private void setStyle(){
        setClassName("item-view");
        name.setClassName("item-view-name-text");
        date.setClassName("item-view-date-text");
    }
}
