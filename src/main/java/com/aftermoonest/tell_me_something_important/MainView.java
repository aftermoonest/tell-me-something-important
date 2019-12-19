package com.aftermoonest.tell_me_something_important;

import com.aftermoonest.tell_me_something_important.strings.Strings;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.HorizontalAlign;
import com.vaadin.flow.component.charts.model.VerticalAlign;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@StyleSheet("frontend://styles/styles.css")
public class MainView extends VerticalLayout {

    private Button submitButton = new Button(Strings.submitButton);
    private Label usernameLabel = new Label(Strings.usernameLabel);
    private TextField usernameField = new TextField();
    private Label usernameTip = new Label(Strings.usernameTip);
    private H1 somethingImportantLabel = new H1(Strings.somethingImportantLabel);
    private TextArea somethingImportantField = new TextArea();

    public MainView() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("main-view");

        askSomethingImportant();
        askUsername();
        setSubmitButton();
        setSize();
        setPlaceholder();
        setItem();
        setOnClickListener();
    }

    private void askSomethingImportant() {
        VerticalLayout somethingImportantLayout = new VerticalLayout();

        somethingImportantLayout.add(somethingImportantLabel, somethingImportantField);
        somethingImportantLayout.setAlignItems(Alignment.CENTER);
        add(somethingImportantLayout);
    }

    private void askUsername() {
        VerticalLayout usernameLayout = new VerticalLayout();
        HorizontalLayout usernameEnterLayout = new HorizontalLayout();

        usernameEnterLayout.add(usernameLabel, usernameField);
        usernameEnterLayout.setWidth("75%");
        usernameLayout.add(usernameEnterLayout, usernameTip);
        usernameLayout.setAlignItems(Alignment.CENTER);

        add(usernameLayout);
    }

    private void setSubmitButton() {
        HorizontalLayout submitLayout = new HorizontalLayout();

        submitLayout.add(submitButton);

        add(submitLayout);
    }

    private void setOnClickListener(){
        submitButton.addClickListener(click -> {
            add(new ItemView());
        });
    }

    private void setSize() {
        usernameField.setWidth("50%");
        somethingImportantField.setWidth("75%");
    }

    private void setPlaceholder() {
        somethingImportantField.setPlaceholder(Strings.somethingImportantLabel);
        usernameField.setPlaceholder(Strings.usernameLabel);
    }

    private void setItem(){
        ItemView itemView = new ItemView();
        add(itemView);
    }
}
