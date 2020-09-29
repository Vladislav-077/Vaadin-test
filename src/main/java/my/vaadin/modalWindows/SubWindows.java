package my.vaadin.modalWindows;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.StringDatatypeValidator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import my.vaadin.MyUI;
import my.vaadin.pojo.User;

import java.text.ParseException;
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
        private BeanItemContainer<User> usersBeanItemContainer;
        private Item user;
        private String nameButton; // Данная переменная нужна для определения функции вызываемого окна.
        private HorizontalLayout horizontalLayoutFormButton;
        private FormLayout verticalLayoutContentForm;
        private Button updateButton;
        private Button createButton;
        private Enum action;
        private Date minValueDate;
        private Date maxValueDate;
        private SimpleDateFormat dateFormat;



        //String caption, Item user, Grid grid, BeanItemContainer<User> usersBeanItemContainer, String nameButton, Object itemI
        public SubWindows(String caption, BeanItemContainer<User> usersBeanItemContainer, Grid grid, Enum action) {
                super(caption); //Заголовок.
                System.out.println(user);
                // Размер окна.
                this.setHeight("520");
                this.setWidth("480");
                this.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
                this.setResizable(false);  // Запрет на растягивание окна.
                this.setDraggable(false); // Запрет на перестаскивание окна.
                this.grid = grid;
                this.usersBeanItemContainer = usersBeanItemContainer;
                this.action = action;
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                maxValueDate = new Date();
                try {
                        minValueDate = dateFormat.parse("01/01/2001");
                }
                catch (ParseException e) {
                        System.out.println("Ошибка парсинга даты");
                        e.getStackTrace();
                }
                System.out.println("Максимальное значение даты " + maxValueDate);
                System.out.println("Миниммальное значение даты " + minValueDate);
                center(); //Расположение по центру.

                firstName.setDescription("Укажите имя");
                firstName.addValidator(new StringLengthValidator("Недостаточная длинна поля",3, 30,false));
                firstName.addValidator(new RegexpValidator("^[а-яЁёА-Я-]+$",true,"Допустима только кирилица"));
                firstName.focus(); // Set focus to the user name
                firstName.setWidth(100, Unit.PERCENTAGE);
                firstName.setIcon(FontAwesome.USER);
                firstName.setTabIndex(1);

                lastName.setDescription("Укажите фамилию");
                lastName.addValidator(new StringLengthValidator("Недостаточная длинна поля",3, 30,false));
                lastName.addValidator(new RegexpValidator("^[а-яЁёА-Я-]+$",true,"Допустима только кирилица"));
                lastName.setWidth(100, Unit.PERCENTAGE);
                lastName.setIcon(FontAwesome.USER);
                lastName.setTabIndex(2);


                otchestvo.setDescription("Укажите отчество");
                otchestvo.addValidator(new StringLengthValidator("Недостаточная длинна поля",3, 30,false));
                otchestvo.addValidator(new RegexpValidator("^[а-яЁёА-Я-]+$",true,"Допустима только кирилица"));
                otchestvo.setWidth(100, Unit.PERCENTAGE);
                otchestvo.setIcon(FontAwesome.USER);
                otchestvo.setTabIndex(3);

                email.setDescription("Укажите email");
                email.addValidator(new EmailValidator("Неправильно указан email"));
//                email.addValidator(new NullValidator("Поле не заполннно",false));
                email.addValidator(new StringLengthValidator("Поле не заполненно",3,30,false));
                email.setWidth(100, Unit.PERCENTAGE);
                email.setIcon(FontAwesome.INBOX);
                email.setTabIndex(4);

                telephone.setDescription("Укажите телефон");
                telephone.addValidator(new StringLengthValidator("мин 3 символа мах = 13 символов",3, 13,false));
                telephone.addValidator(new RegexpValidator("[0-9]+",true,"Допустима только цифры"));
                telephone.setWidth(100, Unit.PERCENTAGE);
                telephone.setIcon(FontAwesome.PHONE);
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
                popupDateField.setRangeStart(minValueDate);
                popupDateField.setRangeEnd(maxValueDate);


                //Горизонтальный Layout с кнопками.
                createButton = new Button();
                buttonCreateAndUpdate(action);

                Button closeButton = closeButton();
                horizontalLayoutFormButton = new HorizontalLayout(createButton,closeButton); // Кнопки создаем через метод
                horizontalLayoutFormButton.setSpacing(true);
                horizontalLayoutFormButton.setWidth(80,Unit.PERCENTAGE);

                //Вертикальный Layout с полями.
                verticalLayoutContentForm = new FormLayout(firstName, lastName, otchestvo, email, telephone, pol, popupDateField);
                verticalLayoutContentForm.setWidth(90,Unit.PERCENTAGE);
                verticalLayoutContentForm.setSpacing(true);

                //Главный слой совмещающий поля и кнопки.
                VerticalLayout verticalLayoutMain = new VerticalLayout(verticalLayoutContentForm, horizontalLayoutFormButton);
                verticalLayoutMain.setWidth(100,Unit.PERCENTAGE);
                verticalLayoutMain.setComponentAlignment(verticalLayoutContentForm,Alignment.MIDDLE_CENTER);
                verticalLayoutMain.setComponentAlignment(horizontalLayoutFormButton,Alignment.MIDDLE_CENTER);
                setContent(verticalLayoutMain);

//                setListeners(); //добавляем листенеры

        }
        public SubWindows(String caption, BeanItemContainer<User> usersBeanItemContainer, Grid grid, Item user,Enum action) {
                this(caption,usersBeanItemContainer,grid,action);
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

                        //Инициализация кнопки updateButton
                        updateButton = new Button();
                        buttonCreateAndUpdate(action);

                        horizontalLayoutFormButton.addComponents(updateButton,closeButton());
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

        // Кнопка обновления пользователя.
        public void buttonCreateAndUpdate(Enum action) {
                if (action.equals(MyUI.Action.UPDATE)) {
                        updateButton = new Button("Подтвердить", FontAwesome.CHECK);
                        updateButton.setWidth(100, Unit.PERCENTAGE);
                        for (Object itemId : grid.getSelectedRows()) {
                                grid.getContainerDataSource().removeItem(itemId);
                                grid.getSelectionModel().reset();
                        }
                        updateButton.addClickListener(event -> {
                                if (firstName.isValid() && lastName.isValid() && otchestvo.isValid() && email.isValid() && telephone.isValid() && pol.isValid() && popupDateField.isValid()) {
                                        usersBeanItemContainer.addBean(
                                                new User(firstName.getValue(), lastName.getValue(), otchestvo.getValue(), email.getValue(), telephone.getValue(), pol.getValue().toString(), popupDateField.getValue()
                                                ));
                                        grid.getSelectionModel().reset();
                                        grid.clearSortOrder();
                                        close();
                                        Notification.show("Пользователь изменен!");
                                }
                                else Notification.show("Проверьте правильность заполненых полей !");
                        });
                }
                else if (action.equals(MyUI.Action.CREATE)) {
                        createButton = new Button("Добавить",FontAwesome.CHECK);
                        createButton.setWidth(100,Unit.PERCENTAGE);
                        createButton.addClickListener(event ->{
                                if (firstName.isValid() && lastName.isValid() && otchestvo.isValid() && email.isValid() && telephone.isValid() && pol.isValid() && popupDateField.isValid()) {
                                        usersBeanItemContainer.addBean(
                                                new User(firstName.getValue(), lastName.getValue(), otchestvo.getValue(), email.getValue(), telephone.getValue(), pol.getValue().toString(), popupDateField.getValue()
                                                ));
                                        close();
                                        grid.getSelectionModel().reset();
                                        Notification.show("Пользователь добавлен!");
                                }
                                else Notification.show("Проверьте правильность заполненых полей !");
                        });
                }
        }
}