package my.vaadin.subWindowsConfirmation;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import my.vaadin.pojo.Users;

public class SubWindowsConfirmation extends Window {
    private Grid grid;
    private BeanItemContainer<Users> usersBeanItemContainer;
    private Item user;
    private String nameButton; // Данная переменная нужна для определения функции вызываемого окна.
    private HorizontalLayout horizontalLayoutFormButton;
    private VerticalLayout verticalLayoutContentForm;


    public SubWindowsConfirmation(String caption, BeanItemContainer<Users> usersBeanItemContainer, Grid grid,Item user) {
        super(caption); //Заголовок.
        // Размер окна.
        this.setHeight("320");
        this.setWidth("480");
        this.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
        this.setResizable(false);  // Запрет на растягивание окна.
        this.setDraggable(false); // Запрет на перестаскивание окна.
        this.grid = grid;
        this.usersBeanItemContainer = usersBeanItemContainer;
        this.nameButton = nameButton;
        center();


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

        VerticalLayout verticalLayoutContent = new VerticalLayout(horizontalLayoutButton);
        verticalLayoutContent.setComponentAlignment(horizontalLayoutButton,Alignment.MIDDLE_CENTER);
        verticalLayoutContent.setHeight(100,Unit.PERCENTAGE);
        setContent(verticalLayoutContent);
    }


}