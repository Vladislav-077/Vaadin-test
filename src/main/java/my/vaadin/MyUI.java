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
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import my.vaadin.modalWindows.SubWindowsUI;
import pojo.Users;


import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.Collection;

@Title("My VaadinApp")
@Theme("mytheme")
public class MyUI extends UI {
    Grid grid; // Создание таблицы.

    @Override
    public void init(VaadinRequest request) {

        // Горизонтальный layout для работы с кнопками расположены с верху таблицы.
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth(50, Unit.PERCENTAGE);

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
        Collection<Users> usersArrayList = new ArrayList<>();
        usersArrayList.add(new Users("Владислав","Абовян","Евгеньевич","blad_04@mail.ru","89152091205"));
        usersArrayList.add(new Users("Егор","Муганов","Иванович","egor_04@mail.ru","89152091205"));
        usersArrayList.add(new Users("Антон","Антон","Антонович","anton_04@mail.ru","89152091205"));
        usersArrayList.add(new Users("Илья","Ильин","Ильич","ilya_04@mail.ru","89152091205"));
        usersArrayList.add(new Users("Григорий","Мелихов","Анатольевич","grigor_04@mail.ru","89152091205"));



        //Создаем контейнер для хранения данных, и помещаем в него колликцию с объектами Users
        BeanItemContainer<Users> usersBeanItemContainer = new BeanItemContainer<Users>(Users.class, usersArrayList);

        // Создаем сетку привязанную к контейнеру.
        grid = new Grid(usersBeanItemContainer); //Инициализация объекта Grid, с помещенным контейнером.
        grid.setCaption("Зарегистрированные пользователи"); // Устанавливаем заголовок таблицы.
        grid.setColumnOrder("firstName","lastName","otchestvo","email","telephone"); // Задали колонки.
        grid.setSelectionMode (Grid.SelectionMode.SINGLE); // В таблице можно выделить только одну запись.
        grid.setWidth(50, Unit.PERCENTAGE);

        //Определение размера таблицы
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(5);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);//Получаем элемент выбранной строки.

        grid.addSelectionListener (e -> {
            // Получаем элемент выбранной строки
            BeanItem <Users> item =
                    usersBeanItemContainer.getItem (grid.getSelectedRow ());
            Notification.show ("Выбран " +
                    item.getBean ().getFirstName());
        });


        gridButton(buttonLayout); // Метод для создания кнопок.
        verticalLayout.addComponents(buttonLayout,grid); //Добавление элементов

        // Выравнивание.
        verticalLayout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(buttonLayout,Alignment.MIDDLE_CENTER);


    }

    public void gridButton(HorizontalLayout buttonLayout) {

        //Кнопка создать
        Button createButton = new Button("Создать");
        createButton.setWidth(100, Unit.PERCENTAGE);
        createButton.addClickListener(listener->{
            System.out.println("Создать");
            // Добавление модального окна.
            SubWindowsUI sub = new SubWindowsUI();
            sub.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
            UI.getCurrent().addWindow(sub); //

        });

        // Кнопка удалить
        Button deleteButton = new Button("Удалить");
        deleteButton.setWidth(100, Unit.PERCENTAGE);
        deleteButton.addClickListener(listener->{
            System.out.println("Удвлить");
        });

        buttonLayout.addComponents(createButton,deleteButton);

    }


@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
