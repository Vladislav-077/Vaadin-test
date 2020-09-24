package my.vaadin.modalWindows.Panel;

import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class Panel extends com.vaadin.ui.Panel {
    public Panel() {
        Panel userFormPanel = new Panel();
        FormLayout userFormContent = new FormLayout();
        userFormPanel.setContent(userFormContent);
        userFormContent.addComponent(userFormPanel);

// Create the first field which will be focused
        final TextField username = new TextField("User name");
        userFormContent.addComponent(username);

// Set focus to the user name
        username.focus();

        final TextField password = new TextField("Password");
        userFormContent.addComponent(password);

        username.setTabIndex(1);
        password.setTabIndex(2);

// Whenever either field loses focus, focus the other field
        username.addBlurListener(new FieldEvents.BlurListener() {
            public void blur(FieldEvents.BlurEvent event) {
                password.focus();
            }
        });

        password.addBlurListener(new FieldEvents.BlurListener() {
            public void blur(FieldEvents.BlurEvent event) {
                username.focus();
            }
        });

        Button login = new Button("Login");
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                // Not really necessary to remove the listeners
                for (Object l: username.getListeners(FieldEvents.BlurEvent.class))
                    username.removeBlurListener((FieldEvents.BlurListener) l);
                for (Object l: password.getListeners(FieldEvents.BlurEvent.class))
                    password.removeBlurListener((FieldEvents.BlurListener) l);

                Notification.show("Bling!");
            }
        });
        userFormContent.addComponent(login);

    }
}
