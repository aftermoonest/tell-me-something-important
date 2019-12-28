package com.aftermoonest.tell_me_something_important.components;

import com.aftermoonest.tell_me_something_important.repository.Item;
import com.aftermoonest.tell_me_something_important.view.ItemView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.List;

public class ItemList {

    private VerticalLayout verticalLayout = new VerticalLayout();

    public VerticalLayout show(List<Item> items){
        verticalLayout.setWidth("75%");
        for (Item item : items) {
            verticalLayout.add(new ItemView(item));
        }
        return verticalLayout;
    }
}
