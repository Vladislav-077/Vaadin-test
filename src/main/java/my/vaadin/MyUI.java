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
        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();

        //Горизонтвальный layout который в котором расположена таблица.
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
        grid.setWidth(70, Unit.PERCENTAGE);

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
        deleteButton.setWidth(100,Unit.PERCENTAGE);
        deleteButton.setEnabled(false);
        deleteUsersForGrid(deleteButton,grid); //Логика кнопки удаления.

        //Действие при двойном нажатии на Grid, форма редактирования записи User
        grid.addItemClickListener(itemClickEvent -> {
            grid.getContainerDataSource().getItem(grid.getSelectedRow());
            Item users = usersBeanItemContainer.getItem(itemClickEvent.getItemId()); // Получение выбранного пользователя.!
            if (itemClickEvent.isDoubleClick()) {
                // Добавление модального окна.
                System.out.println("Открытие PopUp окна для редактирования пользователя");
                SubWindowsUI sub = new SubWindowsUI("Форма редиктирования пользователя",users);
                UI.getCurrent().addWindow(sub); //
            }
        });

        //Добавляем в горизонтальный Layout созданные кнопки.
        horizontalLayoutForButton.addComponents(createButton,deleteButton);
        horizontalLayoutForButton.setWidth(70,Unit.PERCENTAGE);
        horizontalLayoutForButton.setSpacing(true);

        //Соединяем в вертикальный Layout, созданные кнопки и таблицу.
        verticalLayoutGridAndButton.addComponents(horizontalLayoutForButton,grid);

        // Выравнивание.
        verticalLayoutGridAndButton.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
        verticalLayoutGridAndButton.setComponentAlignment(horizontalLayoutForButton,Alignment.MIDDLE_CENTER);


    }

    public void deleteUsersForGrid(Button deleteButton,Grid grid) {
        grid.addSelectionListener(itemClickEvent -> {
            deleteButton.setEnabled (grid.getSelectedRows().size() > 0);
        });

        //Логика кнопки удаления + требутся удалять пользователя из таблицы.
        deleteButton.addClickListener(listener->{
            deleteButton.setEnabled(true);
            System.out.println("Удалить");
            for (Object itemId: grid.getSelectedRows()) {
                grid.getContainerDataSource().removeItem(itemId);
                grid.getSelectionModel().reset();

            }
        });

    }

    public void createNewUserForGrid(Button createButton) {
        createButton.addClickListener(listener->{
            System.out.println("Создать");

            // Добавление модального окна.
            SubWindowsUI sub = new SubWindowsUI("Форма создания нового полозователя", null);
            UI.getCurrent().addWindow(sub); //
        });


    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
