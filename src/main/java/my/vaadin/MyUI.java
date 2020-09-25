package my.vaadin;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.Renderer;
import lombok.SneakyThrows;
import my.vaadin.modalWindows.SubWindowsUI;
import pojo.Users;


import javax.servlet.annotation.WebServlet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Title("Зарегистрированные пользователи")
@Theme("mytheme")
public class MyUI extends UI {
    Grid grid; // Создание таблицы.
    Collection<Users> usersArrayList;
    SimpleDateFormat simpleDateFormat;
    BeanItemContainer<Users> usersBeanItemContainer;

    @Override
    public void init(VaadinRequest request) {

        // Горизонтальный layout для работы с кнопками расположены с верху таблицы.
        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();

        //Горизонтвальный layout который в котором расположена таблица и кнопки
        VerticalLayout verticalLayoutGridAndButton = new VerticalLayout();

        //Инициализируем метод который использует Layout созданные выше.
        gridCollectionUsers(verticalLayoutGridAndButton,horizontalLayoutForButton);

        //Основной вертикальный Layout в котором распологаются все остальные Layout.
        VerticalLayout verticalLayoutMain = new VerticalLayout();
        verticalLayoutMain.setSizeFull(); // Размерность во весь экран.
        verticalLayoutMain.setSpacing(true);
        verticalLayoutMain.addComponents(verticalLayoutGridAndButton); // Добавляем элементы
        verticalLayoutMain.setComponentAlignment(verticalLayoutGridAndButton, Alignment.MIDDLE_CENTER);
        setContent(verticalLayoutMain); // SetContent


    }


    public void gridCollectionUsers(VerticalLayout verticalLayoutGridAndButton, HorizontalLayout horizontalLayoutForButton) {

        //Коллекция Users
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        usersArrayList = new ArrayList<>();
        try {
            usersArrayList.add(new Users("Владислав", "Абовян", "Евгеньевич", "blad_04@mail.ru", "89152091205", "Мужской", simpleDateFormat.parse("22/08/1997")));
            usersArrayList.add(new Users("Егор", "Муганов", "Иванович", "egor_04@mail.ru", "89152091205", "Мужской", simpleDateFormat.parse("05/08/2020")));
            usersArrayList.add(new Users("Антон", "Антон", "Антонович", "anton_04@mail.ru", "89152091205", "Мужской", simpleDateFormat.parse("01/03/1998")));
            usersArrayList.add(new Users("Илья", "Ильин", "Ильич", "ilya_04@mail.ru", "89152091205", "Мужской", simpleDateFormat.parse("22/05/2003")));
            usersArrayList.add(new Users("Григорий", "Мелихов", "Анатольевич", "grigor_04@mail.ru", "89152091205", "Женский", simpleDateFormat.parse("30/07/1974")));
        }
        catch (Exception e ) {
            System.out.println(e.getStackTrace());
        }



        //Создаем контейнер для хранения данных, и помещаем в него колликцию с объектами Users
        usersBeanItemContainer = new BeanItemContainer<Users>(Users.class, usersArrayList);

        // Создаем сетку привязанную к контейнеру.
        grid = new Grid(usersBeanItemContainer); //Инициализация объекта Grid, с помещенным контейнером.
//        grid.getColumn("").setRenderer()
        grid.setEditorEnabled(false);
//        grid.setCaption("Зарегистрированные пользователи"); // Устанавливаем заголовок таблицы.

        // Задаем название колонок
        Grid.Column firstNameColumn = grid.getColumn("firstName");
        firstNameColumn.setHeaderCaption("Имя");
        firstNameColumn.setResizable(false);

        Grid.Column lastNameColumn = grid.getColumn("lastName");
        lastNameColumn.setHeaderCaption("Фамилия");
        lastNameColumn.setResizable(false);

        Grid.Column otchestvoCulumn = grid.getColumn("otchestvo");
        otchestvoCulumn.setHeaderCaption("Отчество");
        otchestvoCulumn.setResizable(false);

        Grid.Column emailColumn = grid.getColumn("email");
        emailColumn.setHeaderCaption("Почта");
        emailColumn.setResizable(false);

        Grid.Column telephoneColumn = grid.getColumn("telephone");
        telephoneColumn.setHeaderCaption("Телефон");
        telephoneColumn.setResizable(false);

        Grid.Column polColumn = grid.getColumn("pol");
        polColumn.setHeaderCaption("Пол");
        polColumn.setResizable(false);

        Grid.Column birthdayColumn = grid.getColumn("birthday");
        birthdayColumn.setHeaderCaption("Дата рождения");
        birthdayColumn.setResizable(false);
        birthdayColumn.setRenderer((Renderer) new DateRenderer("%1$td/%1$tm/%1$tY"));

        grid.setColumnOrder("firstName","lastName","otchestvo","email","telephone","pol","birthday"); // Задается порядок полонок.
        grid.setSelectionMode (Grid.SelectionMode.SINGLE); // В таблице можно выделить только одну запись.
        grid.setWidth(70, Unit.PERCENTAGE);
        // Alternatively, with a custom pattern:

        //Определение размера таблицы
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(7);
        grid.setResponsive(false);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);//Получаем элемент выбранной строки.

        // Предвыбор в таблице.
        Grid.SingleSelectionModel selection = (Grid.SingleSelectionModel) grid.getSelectionModel();
        selection.select(grid.getContainerDataSource().getIdByIndex(2));


        //Кнопка создать
        Button createButton = new Button("Создать",FontAwesome.FILE);
        createButton.setDescription("Создать пользователя");
        createButton.setWidth(100,Unit.PERCENTAGE);
        createNewUserForGrid(createButton);

        // Кнопка удалить
        Button deleteButton = new Button("Удалить",FontAwesome.CLOSE);
        deleteButton.setDescription("Удалить пользователя");
        deleteButton.setWidth(80,Unit.PERCENTAGE);
        deleteButton.setEnabled(false);
        deleteUsersForGrid(deleteButton,grid); //Логика кнопки удаления.

        //Действие при двойном нажатии на Grid, форма редактирования записи User
        readSelectUserForGrid();


        //Добавляем в горизонтальный Layout horizontalLayoutAligmentButton для выравнивания кнопок.
        HorizontalLayout horizontalLayoutAligmentButton = new HorizontalLayout(createButton,deleteButton);
        horizontalLayoutAligmentButton.setWidth(50,Unit.PERCENTAGE);
        horizontalLayoutAligmentButton.setSpacing(true);

        horizontalLayoutForButton.addComponents(horizontalLayoutAligmentButton);
        horizontalLayoutForButton.setComponentAlignment(horizontalLayoutAligmentButton,Alignment.MIDDLE_CENTER);
        horizontalLayoutForButton.setSpacing(false);
        horizontalLayoutForButton.setWidth(50,Unit.PERCENTAGE);

        //Соединяем в вертикальный Layout, созданные кнопки и таблицу.
        verticalLayoutGridAndButton.addComponents(horizontalLayoutForButton,grid);
        verticalLayoutGridAndButton.setComponentAlignment(horizontalLayoutForButton,Alignment.MIDDLE_RIGHT);
        verticalLayoutGridAndButton.setComponentAlignment(grid,Alignment.MIDDLE_CENTER); //Выравнивание таблицы по центру.
        verticalLayoutGridAndButton.setSpacing(true);


    }

    public void readSelectUserForGrid() {
        //Действие при двойном нажатии на Grid, форма редактирования записи User
        grid.addItemClickListener(itemClickEvent -> {
            grid.getContainerDataSource().getItem(grid.getSelectedRow());
            Item user = usersBeanItemContainer.getItem(itemClickEvent.getItemId()); // Получение выбранного пользователя.!
            if (itemClickEvent.isDoubleClick()) {
                // Добавление модального окна.
                System.out.println("Открытие PopUp окна для просмотра пользователя");
                SubWindowsUI sub = new SubWindowsUI("Форма просмотра пользователя",user,grid,usersBeanItemContainer);
                UI.getCurrent().addWindow(sub); //
            }
        });
    }

    public void deleteUsersForGrid(Button deleteButton,Grid grid) {
        grid.addSelectionListener(itemClickEvent -> {
            deleteButton.setEnabled (grid.getSelectedRows().size() > 0);
        });

        //Логика кнопки удаления + требутся удалять пользователя из таблицы.
        deleteButton.addClickListener(listener->{
            System.out.println("Удалить");
            deleteButton.setEnabled(true);
            for (Object itemId: grid.getSelectedRows()) {
                //Открытие PopUp окна.
                Window subWindowsDelete =  new Window("Подтвердите удаление пользователя");
                subWindowsDelete.setHeight("250");
                subWindowsDelete.setWidth("480");
                subWindowsDelete.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
                subWindowsDelete.setResizable(false);  // Запрет на растягивание окна.
                subWindowsDelete.setDraggable(false); // Запрет на перестаскивание окна.
                subWindowsDelete.center();


                VerticalLayout verticalLayout = new VerticalLayout();
                HorizontalLayout horizontalLayout = new HorizontalLayout();
                verticalLayout.setSizeFull();
                verticalLayout.addComponent(horizontalLayout);
                verticalLayout.setComponentAlignment(horizontalLayout,Alignment.MIDDLE_CENTER);


                // Кнопка отмена удаления
                Button cancel = new Button("Отмена", FontAwesome.CLOSE);
                cancel.setWidth(100,Unit.PERCENTAGE);
                cancel.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        System.out.println("Отмена удаления пользователя");
                        subWindowsDelete.close();
                    }
                });
                // Кнопка подтверждения удаления.
                Button deleteButtunComplite = new Button("Удалить", FontAwesome.CHECK);
                deleteButtunComplite.setWidth(100,Unit.PERCENTAGE);
                deleteButtunComplite.addClickListener(new Button.ClickListener() {
                    @SneakyThrows
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        System.out.println("Подтверждение удаления пользователя");
                        grid.getContainerDataSource().removeItem(itemId);
                        subWindowsDelete.close();
                        Thread.sleep(300);
                        Notification.show("Пользователь удален!");
                        grid.getSelectionModel().reset();
                    }
                });

                horizontalLayout.addComponents(deleteButtunComplite,cancel);
                horizontalLayout.setSpacing(true);
                subWindowsDelete.setContent(verticalLayout);
                UI.getCurrent().addWindow(subWindowsDelete);


            }
        });

    }

    public void createNewUserForGrid(Button createButton) {
        createButton.addClickListener(listener->{
            System.out.println("Создать");

            // Добавление модального окна.
            SubWindowsUI sub = new SubWindowsUI("Форма создания нового полозователя", null,grid,usersBeanItemContainer);
            UI.getCurrent().addWindow(sub); //
        });


    }
//    private class LocalDateTimeCustomRender extends Renderers {
//
//    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
