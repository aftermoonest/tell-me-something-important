package com.aftermoonest.tell_me_something_important.view;

import com.aftermoonest.tell_me_something_important.repository.Item;
import com.aftermoonest.tell_me_something_important.components.ItemList;
import com.aftermoonest.tell_me_something_important.repository.Controller;
import com.aftermoonest.tell_me_something_important.repository.ControllerImpl;
import com.aftermoonest.tell_me_something_important.strings.Values;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Route("")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@StyleSheet("frontend://styles/styles-main-view.css")
@PageTitle("TellMeSomethingImportant")
public class MainView extends VerticalLayout {

    private final Controller controller = new ControllerImpl();

    private final TextArea textField = new TextArea(null, Values.somethingImportantLabel);
    private final TextField usernameField = new TextField(Values.usernameLabel, Values.usernameTip);
    private final EmailField emailField = new EmailField(Values.emailLabel, Values.emailLabel);

    private final H1 somethingImportantLabel = new H1(Values.somethingImportantLabel);

    private final Button submitButton = new Button(Values.submit, VaadinIcon.CHECK.create());

    private final Notification errorTextNotification = new Notification(Values.textError, 5000);
    private final Notification errorEmailNotification = new Notification(Values.emailError, 5000);
    private final Notification successAddingNotification = new Notification(Values.successAdding, 5000);

    private final DialogView alreadyInDatabaseDialog = new DialogView(Values.alreadyInDatabase);

    private VerticalLayout enteringInfoLayout = new VerticalLayout();

    private VerticalLayout itemsLayout = new VerticalLayout();
    private VerticalLayout additionalInfoLayout = new VerticalLayout();
    private HorizontalLayout additionalInfoBodyLayout = new HorizontalLayout();

    private VerticalLayout footerLayout = new VerticalLayout();

    @Autowired
    public MainView() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        setClassName("main-view");

        setHeader();

        askText();
        setAdditionalInfoLayout();
        setSubmitButton();

        setItems();
        add(footerLayout);
        setKeyListener();
    }

    private void askText() {
        VerticalLayout somethingImportantLayout = new VerticalLayout();

        textField.setClearButtonVisible(true);
        textField.setWidth("75%");

        if (textField.getValue() == null || textField.getValue().trim().equals("")) {
            textField.setErrorMessage(Values.textError);
        }

        somethingImportantLayout.add(somethingImportantLabel, textField);

        somethingImportantLayout.setAlignItems(Alignment.CENTER);

        add(somethingImportantLayout);
    }

    private void setAdditionalInfoLayout() {
        additionalInfoLayout.setAlignItems(Alignment.CENTER);

        askEmail();
        askUsername();
        additionalInfoLayout.add(additionalInfoBodyLayout);
        additionalInfoBodyLayout.setWidth("75%");
        add(additionalInfoLayout);
    }

    private void askEmail() {
        HorizontalLayout emailEnterLayout = new HorizontalLayout();
        emailEnterLayout.setWidth("100%");

        emailField.setClearButtonVisible(true);
        emailField.setErrorMessage(Values.emailIncorrect);
        emailField.setWidth("75%");

        emailEnterLayout.add(emailField);

        additionalInfoBodyLayout.add(emailEnterLayout);
    }

    private void askUsername() {
        HorizontalLayout usernameEnterLayout = new HorizontalLayout();
        usernameEnterLayout.setWidth("100%");

        usernameField.setClearButtonVisible(true);
        usernameField.setWidth("75%");

        usernameEnterLayout.add(usernameField);

        additionalInfoBodyLayout.add(usernameEnterLayout);
    }

    private void setSubmitButton() {
        HorizontalLayout submitLayout = new HorizontalLayout();
        submitLayout.add(submitButton);

        submitButton.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent ->
        {
            if (controller.find(Integer.toString(emailField.getValue().hashCode()))) {
                errorEmailNotification.open();
            }
            addItemAndHideButton();
        });

        add(submitLayout);
    }

    private void setKeyListener() {
        textField.addKeyPressListener(Key.ENTER, e -> {
            emailField.focus();
        }, KeyModifier.SHIFT);

        emailField.addKeyPressListener(Key.ENTER, e -> {
            usernameField.focus();
        });

        usernameField.addKeyPressListener(Key.ENTER, e -> {
            addItemAndHideButton();
        });
    }

    private void addItemAndHideButton() {
        addItem(
                usernameField.getValue(),
                emailField.getValue(),
                LocalDate.now(),
                textField.getValue()
        );
    }

    private void addItem(String name, String email, LocalDate date, String text) {
        if (!controller.isTextCorrect(text)) {
            errorTextNotification.open();
        } else if (!controller.isEmailCorrect(emailField)) {
            errorEmailNotification.open();
        } else {
            checkIfInDatabaseAndSave(name, email, date, text);
            clearFields();
        }
    }

    private void checkIfInDatabaseAndSave(String name, String email, LocalDate date, String text) {
        if (controller.find(email)) {
            alreadyInDatabaseDialog.open();
            clearFields();
        } else {
            controller.save(controller.buildItem(email, name, date.toString(), text), email);
            setItems();
        }
    }

    private void clearFields() {
        textField.clear();
        usernameField.clear();
        emailField.clear();
    }

    private void setItems() {
        //TODO : робить через жопу, треба зробити більш краще
        ItemList itemList = new ItemList();

        remove(itemsLayout);
        List<Item> items = controller.get();
        System.out.println(Arrays.toString(items.toArray()));

        itemsLayout = itemList.show(items);
        add(itemsLayout);
    }

    private void setHeader() {
        HeaderView headerView = new HeaderView();
        add(headerView);
    }
}