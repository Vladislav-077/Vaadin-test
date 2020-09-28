package my.vaadin.modalWindows;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import my.vaadin.MyUI;
import my.vaadin.pojo.Users;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SubWindows extends Window {
        Button buttonClose;
        Button buttonCreate;
        final TextField firstName = new TextField("Имя");
        final TextField lastName = new TextField("Фамилия");
        final TextField otchestvo = new TextField("Отчество");
        final TextField email = new TextField("Почта");
        final TextField telephone = new TextField("Телефон");
        final ComboBox pol = new ComboBox("Пол");
        final PopupDateField popupDateField = new PopupDateField("Дата рождения");
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        private Grid grid;
        private BeanItemContainer<Users> usersBeanItemContainer;
        private Item user;
        private String nameButton; // Данная переменная нужна для определения функции вызываемого окна.
        private HorizontalLayout horizontalLayoutFormButton;
        private VerticalLayout verticalLayoutContentForm;



        //String caption, Item user, Grid grid, BeanItemContainer<Users> usersBeanItemContainer, String nameButton, Object itemI
        public SubWindows(String caption, BeanItemContainer<Users> usersBeanItemContainer,Grid grid) {
                super(caption); //Заголовок.
                System.out.println(user);
                // Размер окна.
                this.setHeight("620");
                this.setWidth("480");
                this.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
                this.setResizable(false);  // Запрет на растягивание окна.
                this.setDraggable(false); // Запрет на перестаскивание окна.
                this.grid = grid;
                this.usersBeanItemContainer = usersBeanItemContainer;
                this.nameButton = nameButton;
                center();

                firstName.setDescription("Укажите имя");
                firstName.focus(); // Set focus to the user name
                firstName.setWidth(100, Unit.PERCENTAGE);
                firstName.setIcon(FontAwesome.USER);
                firstName.setTabIndex(1);

                lastName.setIcon(FontAwesome.USER);
                lastName.setDescription("Укажите фамилию");
                lastName.setWidth(100, Unit.PERCENTAGE);
                lastName.setTabIndex(2);

                otchestvo.setIcon(FontAwesome.USER);
                otchestvo.setDescription("Укажите отчество");
                otchestvo.setWidth(100, Unit.PERCENTAGE);
                otchestvo.setTabIndex(3);

                email.setIcon(FontAwesome.INBOX);
                email.setDescription("Укажите email");
                email.setWidth(100, Unit.PERCENTAGE);
                email.setTabIndex(4);

                telephone.setIcon(FontAwesome.PHONE);
                telephone.setDescription("Укажите телефон");
                telephone.setWidth(100, Unit.PERCENTAGE);
                telephone.setTabIndex(5);

                pol.setIcon(FontAwesome.MALE);
                pol.setDescription("Укажите пол");
                pol.setWidth(100, Unit.PERCENTAGE);
                pol.addItems("Мужской", "Женский");
                pol.select("Мужской");
                pol.setTextInputAllowed(false);
                pol.setNullSelectionAllowed(false);

                //Дата рождения. PopUp;
                popupDateField.setWidth(100, Unit.PERCENTAGE);
                popupDateField.setIcon(FontAwesome.BIRTHDAY_CAKE);
                popupDateField.setTextFieldEnabled(false);
                popupDateField.setValue(new Date());
                popupDateField.setDateFormat("dd/MM/yyyy");
                popupDateField.setBuffered(true);
                popupDateField.setLocale(new Locale("ru"));


                //Горизонтальный Layout с кнопками.
                horizontalLayoutFormButton = new HorizontalLayout(createButton(),closeButton()); // Кнопки создаем через метод
                horizontalLayoutFormButton.setSpacing(true);
                horizontalLayoutFormButton.setWidth(100,Unit.PERCENTAGE);

                //Вертикальный Layout с полями.
                verticalLayoutContentForm = new VerticalLayout(firstName, lastName, otchestvo, email, telephone, pol, popupDateField,horizontalLayoutFormButton);
                verticalLayoutContentForm.setWidth(70,Unit.PERCENTAGE);
                verticalLayoutContentForm.setSpacing(true);

                //Главный слой совмещающий поля и кнопки.
                VerticalLayout verticalLayoutMain = new VerticalLayout(verticalLayoutContentForm);
                verticalLayoutMain.setComponentAlignment(verticalLayoutContentForm,Alignment.MIDDLE_CENTER);
                setContent(verticalLayoutMain);

        }
        public SubWindows(String caption, BeanItemContainer<Users> usersBeanItemContainer,Grid grid,Item user,Enum action) {
                this(caption,usersBeanItemContainer,grid);
                this.user = user;
                firstName.setValue(user.getItemProperty("firstName").getValue().toString());
                firstName.setEnabled(false);

                lastName.setValue(user.getItemProperty("lastName").getValue().toString());
                lastName.setEnabled(false);

                otchestvo.setValue(user.getItemProperty("otchestvo").getValue().toString());
                otchestvo.setEnabled(false);

                email.setValue(user.getItemProperty("email").getValue().toString());
                email.setEnabled(false);

                telephone.setValue(user.getItemProperty("telephone").getValue().toString());
                telephone.setEnabled(false);

                pol.setValue(user.getItemProperty("pol").getValue().toString());
                pol.setEnabled(false);

                popupDateField.setValue((Date) user.getItemProperty("birthday").getValue());
                popupDateField.setEnabled(false);

                if (action.equals(MyUI.Action.READ)) {
                        System.out.println("Чтение");
                        horizontalLayoutFormButton.removeAllComponents();
                        horizontalLayoutFormButton.addComponent(closeButton());
                }
                else if (action.equals(MyUI.Action.UPDATE)) {
                        System.out.println("Редактирование");
                        firstName.setEnabled(true);
                        lastName.setEnabled(true);
                        otchestvo.setEnabled(true);
                        email.setEnabled(true);
                        telephone.setEnabled(true);
                        pol.setEnabled(true);
                        popupDateField.setEnabled(true);
                        horizontalLayoutFormButton.removeAllComponents();
                        Button createButton = createButton();
                        createButton.setCaption("Подтвердить");
                        horizontalLayoutFormButton.addComponents(createButton,closeButton());
                }


        }
        //Создание кнопки закрытия окна.
        public Button closeButton () {
                        Button closeButton = new Button("Закрыть", FontAwesome.CLOSE);
                        closeButton.setWidth(100, Unit.PERCENTAGE);
                        closeButton.addClickListener(event -> {
                                close();
                        });
                        return  closeButton;
        }

        // Кнопка добавления нового пользователя.
        public Button createButton(){
                Button createButton = new Button("Добавить",FontAwesome.CHECK);
                createButton.setWidth(100,Unit.PERCENTAGE);

                createButton.addClickListener(event ->{
                        usersBeanItemContainer.addBean(
                                new Users(firstName.getValue(),lastName.getValue(),otchestvo.getValue(),email.getValue(),telephone.getValue(),pol.getValue().toString(),popupDateField.getValue()
                                ));
                        close();
                        grid.getSelectionModel().reset();
                        Notification.show("Пользователь добавлен!");
                });
                return  createButton;
        }
}