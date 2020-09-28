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
//import my.vaadin.modalWindows.SubWindows;
import my.vaadin.modalWindows.SubWindows;
import my.vaadin.pojo.Users;


import javax.servlet.annotation.WebServlet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

@Title("Зарегистрированные пользователи")
@Theme("mytheme")
public class MyUI extends UI {
    Grid grid; // Создание таблицы.
    Collection<Users> usersArrayList;
    SimpleDateFormat simpleDateFormat;
    BeanItemContainer<Users> usersBeanItemContainer;
    SubWindows sub;
    Item user;
    Object userId;

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
        grid.setEditorEnabled(false);
        grid.setColumnOrder("firstName","lastName","otchestvo","email","telephone","pol","birthday"); // Задается порядок полонок.
        grid.setSelectionMode (Grid.SelectionMode.SINGLE); // В таблице можно выделить только одну запись.
        grid.setWidth(100, Unit.PERCENTAGE);

        //Определение размера таблицы
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(7);
        grid.setResponsive(false);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);//Получаем элемент выбранной строки.

        // Задаем название колонок
//        Grid.Column firstNameColumn = grid.getColumn("firstName");
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

        //Текстовое поле найти.
        TextField textFieldFind = new TextField();
        textFieldFind.setDescription("поле поиска");
        textFieldFind.setWidth(100,Unit.PERCENTAGE);

        //Кнопка найти.
        Button findButton = new Button("Найти",FontAwesome.GOOGLE);
        findButton.setCaption("Найти");
        findButton.setWidth(100,Unit.PERCENTAGE);


        //Кнопка создать
        Button createButton = new Button("Создать",FontAwesome.FILE);
        createButton.setDescription("Создать пользователя");
        createButton.setWidth(100,Unit.PERCENTAGE);
        createButton.addClickListener(listener->{
            System.out.println("Создать");
            // Добавление модального окна.
            if (sub == null) {
                sub = new SubWindows("Форма создания нового полозователя",usersBeanItemContainer);
                UI.getCurrent().addWindow(sub); //
                sub.addCloseListener(closeEvent -> {
                    sub = null;
                });
            }
        });

        //Кнопка редактирования выбранной записи.
        Button updateButton = new Button("Редактировать", FontAwesome.BITCOIN);
        updateButton.setDescription("Редактировать выбранного пользователя");
        updateButton.setWidth(100,Unit.PERCENTAGE);
        updateButton.setEnabled(false);

        // Кнопка удалить
        Button deleteButton = new Button("Удалить",FontAwesome.CLOSE);
        deleteButton.setDescription("Удалить пользователя");
        deleteButton.setWidth(100,Unit.PERCENTAGE);
        deleteButton.setEnabled(false);



        //Добавляем в горизонтальный Layout horizontalLayoutAligmentButton для выравнивания кнопок.
        HorizontalLayout horizontalLayoutAligmentButton = new HorizontalLayout(textFieldFind,findButton,createButton,updateButton,deleteButton);
        horizontalLayoutAligmentButton.setWidth(50,Unit.PERCENTAGE);
        horizontalLayoutAligmentButton.setSpacing(true);

        horizontalLayoutForButton.addComponents(horizontalLayoutAligmentButton);
        horizontalLayoutForButton.setComponentAlignment(horizontalLayoutAligmentButton,Alignment.MIDDLE_RIGHT);
        horizontalLayoutForButton.setSpacing(false);
        horizontalLayoutForButton.setWidth(100,Unit.PERCENTAGE);

        //Соединяем в вертикальный Layout, созданные кнопки и таблицу.
        verticalLayoutGridAndButton.addComponents(horizontalLayoutForButton,grid);
        verticalLayoutGridAndButton.setComponentAlignment(horizontalLayoutForButton,Alignment.MIDDLE_RIGHT);
        verticalLayoutGridAndButton.setComponentAlignment(grid,Alignment.MIDDLE_CENTER); //Выравнивание таблицы по центру.
        verticalLayoutGridAndButton.setSpacing(true);


    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
