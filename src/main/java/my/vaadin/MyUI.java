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
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import my.vaadin.modalWindows.Button.PopUpButton;
import my.vaadin.modalWindows.SubWindowsUI;
import pojo.Users;


import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Title("My VaadinApp")
@Theme("mytheme")
public class MyUI extends UI {
    Grid grid; // Создание таблицы.
    Date date;
    Collection<Users> usersArrayList;
    BeanItemContainer<Users> usersBeanItemContainer;

    @Override
    public void init(VaadinRequest request) {

        // Горизонтальный layout для работы с кнопками расположены с верху таблицы.
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth(50, Unit.PERCENTAGE);
        buttonLayout.setSpacing(true);

        //Горизонтвальный layout который в котором расположена таблица.
        VerticalLayout verticalLayoutGridAndButton = new VerticalLayout();
        verticalLayoutGridAndButton.setWidth(100,Unit.PERCENTAGE);




        //Основной вертикальный Layout в котором распологаются все остальные Layout.
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull(); // Размерность во весь экран.
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(verticalLayoutGridAndButton); // Добавляем элементы
        verticalLayout.setComponentAlignment(verticalLayoutGridAndButton, Alignment.MIDDLE_CENTER);
        gridCollectionUsers(verticalLayoutGridAndButton, buttonLayout); // Методл для создания таблицы.
        setContent(verticalLayout); // SetContent
    }


    public void gridCollectionUsers(VerticalLayout verticalLayout, HorizontalLayout buttonLayout) {

        //Коллекция Users
        date = new Date();
        usersArrayList = new ArrayList<>();
        usersArrayList.add(new Users("Владислав","Абовян","Евгеньевич","blad_04@mail.ru","89152091205","Мужчина",date));
        usersArrayList.add(new Users("Егор","Муганов","Иванович","egor_04@mail.ru","89152091205","Мужчина",date));
        usersArrayList.add(new Users("Антон","Антон","Антонович","anton_04@mail.ru","89152091205","Мужчина",date));
        usersArrayList.add(new Users("Илья","Ильин","Ильич","ilya_04@mail.ru","89152091205","Мужчина",date));
        usersArrayList.add(new Users("Григорий","Мелихов","Анатольевич","grigor_04@mail.ru","89152091205","Мужчина",date));



        //Создаем контейнер для хранения данных, и помещаем в него колликцию с объектами Users
        usersBeanItemContainer = new BeanItemContainer<Users>(Users.class, usersArrayList);

        // Создаем сетку привязанную к контейнеру.
        grid = new Grid(usersBeanItemContainer); //Инициализация объекта Grid, с помещенным контейнером.
        grid.setFrozenColumnCount(5); //Запретить возможность двигать колонки. НЕ РАБОТАЕТ.
        grid.setEditorEnabled(false);
        grid.setCaption("Зарегистрированные пользователи"); // Устанавливаем заголовок таблицы.

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

        grid.setColumnOrder("firstName","lastName","otchestvo","email","telephone"); // Задается порядок полонок.
        grid.setSelectionMode (Grid.SelectionMode.SINGLE); // В таблице можно выделить только одну запись.
        grid.setWidth(55, Unit.PERCENTAGE);

        //Определение размера таблицы
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(5);
        grid.setResponsive(false);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);//Получаем элемент выбранной строки.

        // Получаем элемент выбранной строки
//        grid.addSelectionListener (e -> {
//            BeanItem <Users> item = usersBeanItemContainer.getItem (grid.getSelectedRow ());
//            Notification.show ("Выбран: " + item.getBean ().getFirstName() + " " + item.getBean().getLastName() + " " + item.getBean().getOtchestvo());
//        });

        //Открытия окна редактирования при двойном нажатии на запись.
        grid.addItemClickListener(itemClickEvent -> {
            if (itemClickEvent.isDoubleClick()) {
                // Добавление модального окна.
                System.out.println("Открытие PopUp окна для редактирования пользователя");

                grid.getContainerDataSource().getItem(grid.getSelectedRow());
                Item users = usersBeanItemContainer.getItem(itemClickEvent.getItemId()); // Получение выбранного пользователя.!
                SubWindowsUI sub = new SubWindowsUI("Форма редиктирования пользователя",users);
                UI.getCurrent().addWindow(sub); //
            }
        });

        // Предвыбор в таблице.
        Grid.SingleSelectionModel selection =
                (Grid.SingleSelectionModel) grid.getSelectionModel();
        selection.select( // Select 3rd item
                grid.getContainerDataSource().getIdByIndex(2));



        gridButton(buttonLayout); // Метод для создания кнопок.
        verticalLayout.addComponents(buttonLayout,grid); //Добавление элементов

        // Выравнивание.
        verticalLayout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(buttonLayout,Alignment.MIDDLE_CENTER);


    }

    public void gridButton(HorizontalLayout buttonLayout) {

        //Кнопка создать
        PopUpButton createButton = new PopUpButton("Создать",100,"Создать пользователя", FontAwesome.FILE);

        // Кнопка удалить
        PopUpButton deleteButton = new PopUpButton("Удалить", 50,"Удалить пользователя", FontAwesome.CLOSE);


//        Button deleteButton = new Button("Удалить", e -> {
//
//            for (Object itemId: grid.getSelectedRows())
//                grid.getContainerDataSource().removeItem(itemId);
//                grid.getSelectionModel().reset();
//        });
//
//        deleteButton.setEnabled(false); // Отключить по умолчанию.
//        deleteButton.setWidth(100, Unit.PERCENTAGE);
//        deleteButton.setDescription("Удалить пользователя");
//        grid.addSelectionListener(itemClickEvent -> {
//            deleteButton.setEnabled (grid.getSelectedRows().size() > 0); // Уменьшение таблицы.
//        });




        //Логика кнопки удаления + требутся удалять пользователя из таблицы.
        deleteButton.addClickListener(listener->{
            System.out.println("Удалить");
        });


        createButton.addClickListener(listener->{
            System.out.println("Создать");
            // Добавление модального окна.
            SubWindowsUI sub = new SubWindowsUI("Форма создания нового полозователя", null);
            UI.getCurrent().addWindow(sub); //

        });

        buttonLayout.addComponents(createButton,deleteButton); // Добавление кнопок добавить и удалить в Layout

    }


@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
