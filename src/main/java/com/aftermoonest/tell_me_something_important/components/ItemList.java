package com.aftermoonest.tell_me_something_important.components;

import com.aftermoonest.tell_me_something_important.view.ItemView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.List;

public class ItemList {

    private List<Item> itemsList;

    private VerticalLayout verticalLayout = new VerticalLayout();

    public VerticalLayout show(List<Item> items){
        itemsList = items;
        verticalLayout.setWidth("75%");
        for (Item item : itemsList) {
            verticalLayout.add(new ItemView(item));
        }
        return verticalLayout;
    }
}
