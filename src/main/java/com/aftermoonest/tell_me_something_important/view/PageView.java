package com.aftermoonest.tell_me_something_important.view;

import com.aftermoonest.tell_me_something_important.components.ItemList;
import com.aftermoonest.tell_me_something_important.repository.Controller;
import com.aftermoonest.tell_me_something_important.repository.ControllerImpl;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("list")
public class PageView extends VerticalLayout {
    Controller controller = new ControllerImpl();

    VerticalLayout itemsLayout;

    public PageView() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        ItemList list = new ItemList();

        itemsLayout = list.show(controller.get());
        add(itemsLayout);
    }
}
