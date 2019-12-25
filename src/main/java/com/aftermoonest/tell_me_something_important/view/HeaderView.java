package com.aftermoonest.tell_me_something_important.view;

import com.aftermoonest.tell_me_something_important.component.ClickIcon;
import com.aftermoonest.tell_me_something_important.component.ClickLabel;
import com.aftermoonest.tell_me_something_important.strings.Values;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@StyleSheet("frontend://styles/styles-header.css")
class HeaderView extends HorizontalLayout {

    private ClickLabel about = new ClickLabel(Values.about);
    private ClickLabel manifest = new ClickLabel(Values.manifest);

    private DialogView dialogAbout = new DialogView(Values.dialogAbout);
    private DialogView dialogManifest = new DialogView(Values.dialogManifest);

    private ClickIcon github = new ClickIcon();

    @Autowired
    HeaderView() {
        setAlignItems(Alignment.START);

        setClassName("header-view");

        add(about, manifest, github);

        setClickListener();
    }

    private void setClickListener() {
        about.addClickListener(e -> dialogAbout.open());
        manifest.addClickListener(e -> dialogManifest.open());
    }
}
