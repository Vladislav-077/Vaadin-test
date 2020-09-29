package my.vaadin.subWindowsConfirmation;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import my.vaadin.pojo.User;

public class SubWindowsConfirmation extends Window {
    private Grid grid;
    private BeanItemContainer<User> usersBeanItemContainer;
    private Item user;
    private String nameButton; // Данная переменная нужна для определения функции вызываемого окна.
    private HorizontalLayout horizontalLayoutFormButton;
    private VerticalLayout verticalLayoutContentForm;


    public SubWindowsConfirmation(String caption, BeanItemContainer<User> usersBeanItemContainer, Grid grid, Item user) {
        super(caption); //Заголовок.
        // Размер окна.
        this.setHeight("250");
        this.setWidth("480");
        this.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
        this.setResizable(false);  // Запрет на растягивание окна.
        this.setDraggable(false); // Запрет на перестаскивание окна.
        this.grid = grid;
        this.usersBeanItemContainer = usersBeanItemContainer;
        this.user = user;
        center();

        Label label = new Label("Удалить пользователя : " + user.getItemProperty("firstName").getValue());
        label.setWidth(80,Unit.PERCENTAGE);
        label.addStyleName(Reindeer.LABEL_H2);



        Button closeButton = new Button("Закрыть", FontAwesome.CLOSE);
        closeButton.setWidth(100, Unit.PERCENTAGE);
        closeButton.addClickListener(event -> {
            close();
        });

        Button doneButton = new Button("Подтвердить", FontAwesome.CLOSE);
        doneButton.setWidth(100, Unit.PERCENTAGE);
        doneButton.addClickListener(clickDoneEvent ->{
            for (Object itemId : grid.getSelectedRows()) {
                grid.getContainerDataSource().removeItem(itemId);
                grid.getSelectionModel().reset();
                close();
                Notification.show("Пользователь удален!");
            }
        });


        HorizontalLayout horizontalLayoutButton = new HorizontalLayout(doneButton,closeButton);
        horizontalLayoutButton.setComponentAlignment(closeButton,Alignment.MIDDLE_CENTER);
        horizontalLayoutButton.setSpacing(true);
        horizontalLayoutButton.setWidth(70,Unit.PERCENTAGE);

        VerticalLayout verticalLayoutContent = new VerticalLayout(label,horizontalLayoutButton);
        verticalLayoutContent.setComponentAlignment(horizontalLayoutButton,Alignment.MIDDLE_CENTER);
        verticalLayoutContent.setComponentAlignment(label,Alignment.MIDDLE_CENTER);
        verticalLayoutContent.setHeight(100,Unit.PERCENTAGE);
        setContent(verticalLayoutContent);
    }


}