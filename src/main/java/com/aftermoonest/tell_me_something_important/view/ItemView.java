package com.aftermoonest.tell_me_something_important.view;

import com.aftermoonest.tell_me_something_important.repository.Item;
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
    private HorizontalLayout infoLayout;
    private VerticalLayout contentLayout;
    private Label text;

    @Autowired
    public ItemView(Item item) {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        initializeItem(item);
    }

    void initializeItem(Item item){

        name = new H6(item.getName());
        date = new H6(item.getDate());

        infoLayout = new HorizontalLayout();
        infoLayout.add(name, date);
        contentLayout = new VerticalLayout();

        text = new Label(item.getText());
        contentLayout.add(text);

        setStyle();

        add(infoLayout, contentLayout);
    }

    private void setStyle(){
        setClassName("item-view");
        addClassName("item-view-shadow");
        name.setClassName("item-view-name-text");
        date.setClassName("item-view-date-text");
    }
}
