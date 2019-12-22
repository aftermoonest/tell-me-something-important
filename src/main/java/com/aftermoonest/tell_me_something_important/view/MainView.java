package com.aftermoonest.tell_me_something_important.view;

import com.aftermoonest.tell_me_something_important.component.Item;
import com.aftermoonest.tell_me_something_important.component.ItemList;
import com.aftermoonest.tell_me_something_important.strings.Values;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Route("")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
//@StyleSheet("frontend://styles/styles-main-view.css")
@StyleSheet("frontend://styles/styles-main-view.css")
@PageTitle("TellMeSomethingImportant")
public class MainView extends VerticalLayout {

    private final Button submitButton = new Button(Values.submit, VaadinIcon.CHECK.create());
    private final TextField usernameField = new TextField(Values.usernameLabel, Values.usernameTip);
    private final H1 somethingImportantLabel = new H1(Values.somethingImportantLabel);
    private final TextArea somethingImportantField = new TextArea(null, Values.somethingImportantLabel);
    private final Notification errorNotification = new Notification(Values.errorNotification, 5000);
    private VerticalLayout itemsLayout = new VerticalLayout();

    private List<Item> items = new ArrayList<>();

    @Autowired
    public MainView() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        setClassName("main-view");

        setHeader();

        askSomethingImportant();
        askUsername();
        setSubmitButton();
        setSize();
        setItems();

        //setFooter();
    }

    private void askSomethingImportant() {
        VerticalLayout somethingImportantLayout = new VerticalLayout();

        somethingImportantField.setClearButtonVisible(true);

        somethingImportantLayout.add(somethingImportantLabel, somethingImportantField);

        somethingImportantLayout.setAlignItems(Alignment.CENTER);
        add(somethingImportantLayout);
    }

    private void askUsername() {
        VerticalLayout usernameLayout = new VerticalLayout();
        HorizontalLayout usernameEnterLayout = new HorizontalLayout();

        usernameField.setClearButtonVisible(true);

        usernameEnterLayout.add(usernameField);
        usernameEnterLayout.setWidth("75%");
        usernameLayout.add(usernameEnterLayout);
        usernameLayout.setAlignItems(Alignment.CENTER);

        add(usernameLayout);
    }

    private void setSubmitButton() {
        HorizontalLayout submitLayout = new HorizontalLayout();

        submitLayout.add(submitButton);

        setSubmitButtonListener();

        add(submitLayout);
    }

    private void setSubmitButtonListener() {
        submitButton.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            // TODO : hide button after adding item.
            addItem(
                    usernameField.getValue(),
                    LocalDate.now(),
                    somethingImportantField.getValue()
            );
            somethingImportantField.setValue("");
            usernameField.setValue("");
        });
    }

    private void setSize() {
        usernameField.setWidth("75%");
        somethingImportantField.setWidth("75%");
    }

    private void addItem(String name, LocalDate date, String text) {
        // TODO : add adding elements to database.
        if (text == null || text.equals("")) {
            errorNotification.open();
        } else {
            Item item = new Item(name.trim(), date, text.trim());
            items.add(item);
            setItems();
        }
    }

    private void setItems() {
        ItemList itemList = new ItemList();

        remove(itemsLayout);
        itemsLayout = itemList.show(items);
        add(itemsLayout);
    }

    private void setHeader(){
        HeaderView headerView = new HeaderView();
        add(headerView);
    }

    private void setFooter(){
        FooterView footerView = new FooterView();
        add(footerView);
    }
}
