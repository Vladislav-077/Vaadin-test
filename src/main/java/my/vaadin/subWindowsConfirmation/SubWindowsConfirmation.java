//package my.vaadin.modalWindows;
//import com.vaadin.data.Item;
//import com.vaadin.data.util.BeanItemContainer;
//import com.vaadin.server.FontAwesome;
//import com.vaadin.ui.*;
//import lombok.SneakyThrows;
//import my.vaadin.pojo.Users;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//public class SubWindows extends Window {
//    Button buttonClose;
//    Button buttonCreate;
//
//    final TextField firstName = new TextField("Имя");
//    final TextField lastName = new TextField("Фамилия");
//    final TextField otchestvo = new TextField("Отчество");
//    final TextField email = new TextField("Почта");
//    final TextField telephone = new TextField("Телефон");
//    final ComboBox pol = new ComboBox("Пол");
//    final PopupDateField popupDateField = new PopupDateField("Дата рождения");
//    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//    private Grid grid;
//    private BeanItemContainer<Users> usersBeanItemContainer;
//    //        private Item user;
//    private String nameButton; // Данная переменная нужна для определения функции вызываемого окна.
//
//    public SubWindows(String caption, Item user, Grid grid, BeanItemContainer<Users> usersBeanItemContainer, String nameButton, Object itemId) {
//        super(caption); //Заголовок.
//        System.out.println(user);
//        // Размер окна.
//        this.setHeight("520");
//        this.setWidth("480");
//        this.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
//        this.setResizable(false);  // Запрет на растягивание окна.
//        this.setDraggable(false); // Запрет на перестаскивание окна.
//        this.grid = grid;
//        this.usersBeanItemContainer = usersBeanItemContainer;
//        this.nameButton = nameButton;
//        center();
//
//


//                //Базовое содержимое окна.
//                VerticalLayout mainLayout = new VerticalLayout(); // Создаем вертикальный Layout
//                mainLayout.setMargin(true); // Вшеншие отступы.
//                setClosable(true);// Кнопка закрытия.
//                setContent(mainLayout); //Для отображение элементов в Layout
//
//
//                VerticalLayout crateFormLayout = crateFormLayout(user, itemId);
//                mainLayout.addComponent(crateFormLayout);
//                mainLayout.setComponentAlignment(crateFormLayout, Alignment.MIDDLE_CENTER);


//        /* Создание панели для ввода данных */
//        public VerticalLayout crateFormLayout(Item user, Object itemId) {
//
//                Date date = new Date();
//                VerticalLayout verticalLayoutMainForm = new VerticalLayout();
//                FormLayout userFormContent = new FormLayout();
//
//                if (user == null) {
//                        System.out.println("Требуется создание нового пользователя");
//
//                        // Create the first field which will be focused
//                        firstName.setDescription("Укажите имя");
//                        firstName.focus(); // Set focus to the user name
//                        firstName.setWidth(100, Unit.PERCENTAGE);
//                        firstName.setIcon(FontAwesome.USER);
//                        firstName.setTabIndex(1);
//
//
//                        lastName.setIcon(FontAwesome.USER);
//                        lastName.setDescription("Укажите фамилию");
//                        lastName.setWidth(100, Unit.PERCENTAGE);
//                        lastName.setTabIndex(2);
//
//                        otchestvo.setIcon(FontAwesome.USER);
//                        otchestvo.setDescription("Укажите отчество");
//                        otchestvo.setWidth(100, Unit.PERCENTAGE);
//                        otchestvo.setTabIndex(3);
//
//                        email.setIcon(FontAwesome.INBOX);
//                        email.setDescription("Укажите email");
//                        email.setWidth(100, Unit.PERCENTAGE);
//                        email.setTabIndex(4);
//
//                        telephone.setIcon(FontAwesome.PHONE);
//                        telephone.setDescription("Укажите телефон");
//                        telephone.setWidth(100, Unit.PERCENTAGE);
//                        telephone.setTabIndex(5);
//
//                        pol.setIcon(FontAwesome.MALE);
//                        pol.setDescription("Укажите пол");
//                        pol.setWidth(100, Unit.PERCENTAGE);
//                        pol.addItems("Мужской", "Женский");
//                        pol.select("Мужской");
//                        pol.setTextInputAllowed(false);
//                        pol.setNullSelectionAllowed(false);
//
//                        //Дата рождения. PopUp;
//                        popupDateField.setWidth(100, Unit.PERCENTAGE);
//                        popupDateField.setIcon(FontAwesome.BIRTHDAY_CAKE);
//                        popupDateField.setTextFieldEnabled(false);
//                        popupDateField.setValue(new Date());
//                        popupDateField.setDateFormat("dd/MM/yyyy");
//                        popupDateField.setBuffered(true);
//                        popupDateField.setLocale(new Locale("ru"));
//
//
//                        // Добавляем Field на форму.
//                        userFormContent.addComponents(firstName,lastName,otchestvo,email,telephone,pol,popupDateField);
//
//                        // Layout отвечающий за кнопки.
//                        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
//                        horizontalLayoutForButton.setWidth(100,Unit.PERCENTAGE);
//                        horizontalLayoutForButton.setSpacing(true);
//                        horizontalLayoutForButton.addComponents(buttonCreateUser(buttonCreate, grid, usersBeanItemContainer),buttonClose(buttonClose));
//
//                        // Добавляем Layout содержащий кнопки и поля.
//                        verticalLayoutMainForm.addComponents(userFormContent,horizontalLayoutForButton);
//                        verticalLayoutMainForm.setComponentAlignment(userFormContent, Alignment.MIDDLE_CENTER);
//                        verticalLayoutMainForm.setComponentAlignment(horizontalLayoutForButton, Alignment.MIDDLE_CENTER);
//
//
//                }
//                else if (nameButton.equals("прочитать") && user != null) {
//
//                        // Create the first field which will be focused
//                        firstName.setDescription("Укажите имя");
////                        firstName.addValidator(new StringLengthValidator("Недостаточно символов",3,10,false));
//                        firstName.setNullRepresentation("");
//                        firstName.setNullSettingAllowed(true);
//                        firstName.setValue(user.getItemProperty("firstName").getValue().toString());
//                        firstName.setWidth(100, Unit.PERCENTAGE);
//                        firstName.setIcon(FontAwesome.USER);
//                        firstName.setEnabled(false);
//                        firstName.setTabIndex(1);
//                        firstName.focus(); // Set focus to the user name
//
//                        lastName.setDescription("Укажите фамилию");
//                        lastName.setValue(user.getItemProperty("lastName").getValue().toString());
//                        lastName.setWidth(100, Unit.PERCENTAGE);
//                        lastName.setIcon(FontAwesome.USER);
//                        lastName.setEnabled(false);
//                        lastName.setTabIndex(2);
//
//                        otchestvo.setDescription("Укажите отчество");
//                        otchestvo.setValue(user.getItemProperty("otchestvo").getValue().toString());
//                        otchestvo.setWidth(100, Unit.PERCENTAGE);
//                        otchestvo.setIcon(FontAwesome.USER);
//                        otchestvo.setEnabled(false);
//                        otchestvo.setTabIndex(3);
//
//                        email.setDescription("Укажите email");
//                        email.setValue(user.getItemProperty("email").getValue().toString());
//                        email.setWidth(100, Unit.PERCENTAGE);
//                        email.setIcon(FontAwesome.INBOX);
//                        email.setEnabled(false);
//                        email.setTabIndex(4);
//
//                        telephone.setDescription("Укажите телефон");
//                        telephone.setValue(user.getItemProperty("telephone").getValue().toString());
//                        telephone.setWidth(100, Unit.PERCENTAGE);
//                        telephone.setIcon(FontAwesome.PHONE);
//                        telephone.setEnabled(false);
//                        telephone.setTabIndex(5);
//
//                        pol.setDescription("Укажите пол");
//                        pol.setValue(user.getItemProperty("pol").getValue().toString());
//                        pol.setWidth(100, Unit.PERCENTAGE);
//                        pol.setIcon(FontAwesome.MALE);
//                        pol.addItems("Мужской", "Женский");
////                        pol.select("Мужской");
//                        pol.setValue(user.getItemProperty("pol").getValue().toString());
//                        pol.setTextInputAllowed(false);
//                        pol.setNullSelectionAllowed(false);
//                        pol.setEnabled(false);
//                        pol.setTabIndex(6);
//
//                        //Дата рождения. PopUp
//                        popupDateField.setDescription("Укажите дату рождения");
//                        try {
//                                System.out.println("Дата");
//                                date = (Date) user.getItemProperty("birthday").getValue();
//
//                        }
//                        catch (Exception e) {
//                                System.out.println(e.getStackTrace());
//                        }
//                        popupDateField.setValue(date);
//                        popupDateField.setWidth(100, Unit.PERCENTAGE);
//                        popupDateField.setIcon(FontAwesome.BIRTHDAY_CAKE);
//
//
//                        popupDateField.setDateFormat("dd-MM-yyyy");
//                        popupDateField.setTextFieldEnabled(false);
//                        popupDateField.setBuffered(true);
//                        popupDateField.setLocale(new Locale("ru"));
//                        popupDateField.setEnabled(false);
//
//
//                        // Добавляем Field на //package my.vaadin.modalWindows;
//import com.vaadin.data.Item;
//import com.vaadin.data.util.BeanItemContainer;
//import com.vaadin.server.FontAwesome;
//import com.vaadin.ui.*;
//import lombok.SneakyThrows;
//import my.vaadin.pojo.Users;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//public class SubWindows extends Window {
//    Button buttonClose;
//    Button buttonCreate;
//
//    final TextField firstName = new TextField("Имя");
//    final TextField lastName = new TextField("Фамилия");
//    final TextField otchestvo = new TextField("Отчество");
//    final TextField email = new TextField("Почта");
//    final TextField telephone = new TextField("Телефон");
//    final ComboBox pol = new ComboBox("Пол");
//    final PopupDateField popupDateField = new PopupDateField("Дата рождения");
//    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//    private Grid grid;
//    private BeanItemContainer<Users> usersBeanItemContainer;
//    //        private Item user;
//    private String nameButton; // Данная переменная нужна для определения функции вызываемого окна.
//
//    public SubWindows(String caption, Item user, Grid grid, BeanItemContainer<Users> usersBeanItemContainer, String nameButton, Object itemId) {
//        super(caption); //Заголовок.
//        System.out.println(user);
//        // Размер окна.
//        this.setHeight("520");
//        this.setWidth("480");
//        this.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
//        this.setResizable(false);  // Запрет на растягивание окна.
//        this.setDraggable(false); // Запрет на перестаскивание окна.
//        this.grid = grid;
//        this.usersBeanItemContainer = usersBeanItemContainer;
//        this.nameButton = nameButton;
//        center();
//
//
//
//
////                //Базовое содержимое окна.
////                VerticalLayout mainLayout = new VerticalLayout(); // Создаем вертикальный Layout
////                mainLayout.setMargin(true); // Вшеншие отступы.
////                setClosable(true);// Кнопка закрытия.
////                setContent(mainLayout); //Для отображение элементов в Layout
////
////
////                VerticalLayout crateFormLayout = crateFormLayout(user, itemId);
////                mainLayout.addComponent(crateFormLayout);
////                mainLayout.setComponentAlignment(crateFormLayout, Alignment.MIDDLE_CENTER);
//
//
//    }
////        /* Создание панели для ввода данных */
////        public VerticalLayout crateFormLayout(Item user, Object itemId) {
////
////                Date date = new Date();
////                VerticalLayout verticalLayoutMainForm = new VerticalLayout();
////                FormLayout userFormContent = new FormLayout();
////
////                if (user == null) {
////                        System.out.println("Требуется создание нового пользователя");
////
////                        // Create the first field which will be focused
////                        firstName.setDescription("Укажите имя");
////                        firstName.focus(); // Set focus to the user name
////                        firstName.setWidth(100, Unit.PERCENTAGE);
////                        firstName.setIcon(FontAwesome.USER);
////                        firstName.setTabIndex(1);
////
////
////                        lastName.setIcon(FontAwesome.USER);
////                        lastName.setDescription("Укажите фамилию");
////                        lastName.setWidth(100, Unit.PERCENTAGE);
////                        lastName.setTabIndex(2);
////
////                        otchestvo.setIcon(FontAwesome.USER);
////                        otchestvo.setDescription("Укажите отчество");
////                        otchestvo.setWidth(100, Unit.PERCENTAGE);
////                        otchestvo.setTabIndex(3);
////
////                        email.setIcon(FontAwesome.INBOX);
////                        email.setDescription("Укажите email");
////                        email.setWidth(100, Unit.PERCENTAGE);
////                        email.setTabIndex(4);
////
////                        telephone.setIcon(FontAwesome.PHONE);
////                        telephone.setDescription("Укажите телефон");
////                        telephone.setWidth(100, Unit.PERCENTAGE);
////                        telephone.setTabIndex(5);
////
////                        pol.setIcon(FontAwesome.MALE);
////                        pol.setDescription("Укажите пол");
////                        pol.setWidth(100, Unit.PERCENTAGE);
////                        pol.addItems("Мужской", "Женский");
////                        pol.select("Мужской");
////                        pol.setTextInputAllowed(false);
////                        pol.setNullSelectionAllowed(false);
////
////                        //Дата рождения. PopUp;
////                        popupDateField.setWidth(100, Unit.PERCENTAGE);
////                        popupDateField.setIcon(FontAwesome.BIRTHDAY_CAKE);
////                        popupDateField.setTextFieldEnabled(false);
////                        popupDateField.setValue(new Date());
////                        popupDateField.setDateFormat("dd/MM/yyyy");
////                        popupDateField.setBuffered(true);
////                        popupDateField.setLocale(new Locale("ru"));
////
////
////                        // Добавляем Field на форму.
////                        userFormContent.addComponents(firstName,lastName,otchestvo,email,telephone,pol,popupDateField);
////
////                        // Layout отвечающий за кнопки.
////                        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
////                        horizontalLayoutForButton.setWidth(100,Unit.PERCENTAGE);
////                        horizontalLayoutForButton.setSpacing(true);
////                        horizontalLayoutForButton.addComponents(buttonCreateUser(buttonCreate, grid, usersBeanItemContainer),buttonClose(buttonClose));
////
////                        // Добавляем Layout содержащий кнопки и поля.
////                        verticalLayoutMainForm.addComponents(userFormContent,horizontalLayoutForButton);
////                        verticalLayoutMainForm.setComponentAlignment(userFormContent, Alignment.MIDDLE_CENTER);
////                        verticalLayoutMainForm.setComponentAlignment(horizontalLayoutForButton, Alignment.MIDDLE_CENTER);
////
////
////                }
////                else if (nameButton.equals("прочитать") && user != null) {
////
////                        // Create the first field which will be focused
////                        firstName.setDescription("Укажите имя");
//////                        firstName.addValidator(new StringLengthValidator("Недостаточно символов",3,10,false));
////                        firstName.setNullRepresentation("");
////                        firstName.setNullSettingAllowed(true);
////                        firstName.setValue(user.getItemProperty("firstName").getValue().toString());
////                        firstName.setWidth(100, Unit.PERCENTAGE);
////                        firstName.setIcon(FontAwesome.USER);
////                        firstName.setEnabled(false);
////                        firstName.setTabIndex(1);
////                        firstName.focus(); // Set focus to the user name
////
////                        lastName.setDescription("Укажите фамилию");
////                        lastName.setValue(user.getItemProperty("lastName").getValue().toString());
////                        lastName.setWidth(100, Unit.PERCENTAGE);
////                        lastName.setIcon(FontAwesome.USER);
////                        lastName.setEnabled(false);
////                        lastName.setTabIndex(2);
////
////                        otchestvo.setDescription("Укажите отчество");
////                        otchestvo.setValue(user.getItemProperty("otchestvo").getValue().toString());
////                        otchestvo.setWidth(100, Unit.PERCENTAGE);
////                        otchestvo.setIcon(FontAwesome.USER);
////                        otchestvo.setEnabled(false);
////                        otchestvo.setTabIndex(3);
////
////                        email.setDescription("Укажите email");
////                        email.setValue(user.getItemProperty("email").getValue().toString());
////                        email.setWidth(100, Unit.PERCENTAGE);
////                        email.setIcon(FontAwesome.INBOX);
////                        email.setEnabled(false);
////                        email.setTabIndex(4);
////
////                        telephone.setDescription("Укажите телефон");
////                        telephone.setValue(user.getItemProperty("telephone").getValue().toString());
////                        telephone.setWidth(100, Unit.PERCENTAGE);
////                        telephone.setIcon(FontAwesome.PHONE);
////                        telephone.setEnabled(false);
////                        telephone.setTabIndex(5);
////
////                        pol.setDescription("Укажите пол");
////                        pol.setValue(user.getItemProperty("pol").getValue().toString());
////                        pol.setWidth(100, Unit.PERCENTAGE);
////                        pol.setIcon(FontAwesome.MALE);
////                        pol.addItems("Мужской", "Женский");
//////                        pol.select("Мужской");
////                        pol.setValue(user.getItemProperty("pol").getValue().toString());
////                        pol.setTextInputAllowed(false);
////                        pol.setNullSelectionAllowed(false);
////                        pol.setEnabled(false);
////                        pol.setTabIndex(6);
////
////                        //Дата рождения. PopUp
////                        popupDateField.setDescription("Укажите дату рождения");
////                        try {
////                                System.out.println("Дата");
////                                date = (Date) user.getItemProperty("birthday").getValue();
////
////                        }
////                        catch (Exception e) {
////                                System.out.println(e.getStackTrace());
////                        }
////                        popupDateField.setValue(date);
////                        popupDateField.setWidth(100, Unit.PERCENTAGE);
////                        popupDateField.setIcon(FontAwesome.BIRTHDAY_CAKE);
////
////
////                        popupDateField.setDateFormat("dd-MM-yyyy");
////                        popupDateField.setTextFieldEnabled(false);
////                        popupDateField.setBuffered(true);
////                        popupDateField.setLocale(new Locale("ru"));
////                        popupDateField.setEnabled(false);
////
////
////                        // Добавляем Field на форму.
////                        userFormContent.addComponents(firstName,lastName,otchestvo,email,telephone,pol,popupDateField);
////
////                        // Layout отвечающий за кнопки.
////                        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
////                        horizontalLayoutForButton.setWidth(100,Unit.PERCENTAGE);
////                        horizontalLayoutForButton.setSpacing(true);
////                        horizontalLayoutForButton.addComponents(buttonCreateUser(buttonCreate, grid, usersBeanItemContainer),buttonClose(buttonClose));
////
////                        // Добавляем Layout содержащий кнопки и поля.
////                        verticalLayoutMainForm.addComponents(userFormContent,horizontalLayoutForButton);
////                        verticalLayoutMainForm.setComponentAlignment(userFormContent, Alignment.MIDDLE_CENTER);
////                        verticalLayoutMainForm.setComponentAlignment(horizontalLayoutForButton, Alignment.MIDDLE_CENTER);
////                }
////                else if (nameButton.equals("обновить") && user != null) {
////                        // Create the first field which will be focused
////                        firstName.setDescription("Укажите имя");
//////                        firstName.addValidator(new StringLengthValidator("Недостаточно символов",3,10,false));
////                        firstName.setNullRepresentation("");
////                        firstName.setNullSettingAllowed(true);
////                        firstName.setValue(user.getItemProperty("firstName").getValue().toString());
////                        firstName.setWidth(100, Unit.PERCENTAGE);
////                        firstName.setIcon(FontAwesome.USER);
////                        firstName.setTabIndex(1);
////                        firstName.focus(); // Set focus to the user name
////
////                        lastName.setDescription("Укажите фамилию");
////                        lastName.setValue(user.getItemProperty("lastName").getValue().toString());
////                        lastName.setWidth(100, Unit.PERCENTAGE);
////                        lastName.setIcon(FontAwesome.USER);
////                        lastName.setTabIndex(2);
////
////                        otchestvo.setDescription("Укажите отчество");
////                        otchestvo.setValue(user.getItemProperty("otchestvo").getValue().toString());
////                        otchestvo.setWidth(100, Unit.PERCENTAGE);
////                        otchestvo.setIcon(FontAwesome.USER);
////                        otchestvo.setTabIndex(3);
////
////                        email.setDescription("Укажите email");
////                        email.setValue(user.getItemProperty("email").getValue().toString());
////                        email.setWidth(100, Unit.PERCENTAGE);
////                        email.setIcon(FontAwesome.INBOX);
////                        email.setTabIndex(4);
////
////                        telephone.setDescription("Укажите телефон");
////                        telephone.setValue(user.getItemProperty("telephone").getValue().toString());
////                        telephone.setWidth(100, Unit.PERCENTAGE);
////                        telephone.setIcon(FontAwesome.PHONE);
////                        telephone.setTabIndex(5);
////
////                        pol.setDescription("Укажите пол");
////                        pol.setValue(user.getItemProperty("pol").getValue().toString());
////                        pol.setWidth(100, Unit.PERCENTAGE);
////                        pol.setIcon(FontAwesome.MALE);
////                        pol.addItems("Мужской", "Женский");
//////                        pol.select("Мужской");
////                        pol.setValue(user.getItemProperty("pol").getValue().toString());
////                        pol.setTextInputAllowed(false);
////                        pol.setNullSelectionAllowed(false);
////                        pol.setTabIndex(6);
////
////                        //Дата рождения. PopUp
////                        popupDateField.setDescription("Укажите дату рождения");
////                        try {
////                                System.out.println("Дата");
////                                date = (Date) user.getItemProperty("birthday").getValue();
////
////                        } catch (Exception e) {
////                                System.out.println(e.getStackTrace());
////                        }
////                        popupDateField.setValue(date);
////                        popupDateField.setWidth(100, Unit.PERCENTAGE);
////                        popupDateField.setIcon(FontAwesome.BIRTHDAY_CAKE);
////
////
////                        popupDateField.setDateFormat("dd-MM-yyyy");
////                        popupDateField.setTextFieldEnabled(false);
////                        popupDateField.setBuffered(true);
////                        popupDateField.setLocale(new Locale("ru"));
////
////
////                        // Добавляем Field на форму.
////                        userFormContent.addComponents(firstName,lastName,otchestvo,email,telephone,pol,popupDateField);
////
////
////                        // Layout отвечающий за кнопки.
////                        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
////                        horizontalLayoutForButton.setWidth(100,Unit.PERCENTAGE);
////                        horizontalLayoutForButton.setSpacing(true);
////                        horizontalLayoutForButton.addComponents(updateButton(buttonCreate, grid, usersBeanItemContainer, user, itemId),buttonClose(buttonClose));
////
////                        // Добавляем Layout содержащий кнопки и поля.
////                        verticalLayoutMainForm.addComponents(userFormContent,horizontalLayoutForButton);
////                        verticalLayoutMainForm.setComponentAlignment(userFormContent, Alignment.MIDDLE_CENTER);
////                        verticalLayoutMainForm.setComponentAlignment(horizontalLayoutForButton, Alignment.MIDDLE_CENTER);
////
////
////                }
////
////                return verticalLayoutMainForm;
////        }
////
////
////        public Button buttonClose(Button buttonClose) {
////                buttonClose = new Button("Закрыть",FontAwesome.CLOSE);
////                buttonClose.setWidth(100,Unit.PERCENTAGE);
////                buttonClose.addClickListener(new Button.ClickListener() {
////                        public void buttonClick(Button.ClickEvent event) {
////                                close(); // Close the sub-window
////                        }
////                });
////                return buttonClose;
////
////        }
////
////        public Button buttonCreateUser(Button addButtonUser, Grid grid, BeanItemContainer<Users> usersBeanItemContainer) {
////                addButtonUser = new Button("Добавить",FontAwesome.CHECK);
////                addButtonUser.setWidth(100,Unit.PERCENTAGE);
////                addButtonUser.addClickListener(new Button.ClickListener() {
////                        @SneakyThrows
////                        @Override
////                        public void buttonClick(Button.ClickEvent clickEvent) {
////                                usersBeanItemContainer.addBean(
////                                        new Users(firstName.getValue(),lastName.getValue(),otchestvo.getValue(),email.getValue(),telephone.getValue(),pol.getValue().toString(),popupDateField.getValue()
////                                        ));
////                                close();
////                                Notification.show("Пользователь добавлен!");
////                                grid.getSelectionModel().reset();
////
////                        }
////                });
////                return  addButtonUser;
////        }
////
////        public Button updateButton(Button updateButton, Grid grid, BeanItemContainer<Users> usersBeanItemContainer,Item user, Object itemId) {
////                updateButton = new Button("Изменить",FontAwesome.CHECK);
////                updateButton.setWidth(100,Unit.PERCENTAGE);
////                updateButton.addClickListener(new Button.ClickListener() {
////                        @SneakyThrows
////                        @Override
////                        public void buttonClick(Button.ClickEvent clickEvent) {
//                                System.out.println("Изменить имя");
//                                usersBeanItemContainer.removeItem(itemId);
//                                user.getItemProperty("firstName").setValue(firstName.getValue());
//                                close();
////                                grid.getSelectionModel().reset();
//                                Notification.show("Пользователь изменен!");
//
//
//                        }
//                });
//                return  updateButton;
//        }
//
//}
//                        userFormContent.addComponents(firstName,lastName,otchestvo,email,telephone,pol,popupDateField);
//
//                        // Layout отвечающий за кнопки.
//                        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
//                        horizontalLayoutForButton.setWidth(100,Unit.PERCENTAGE);
//                        horizontalLayoutForButton.setSpacing(true);
//                        horizontalLayoutForButton.addComponents(buttonCreateUser(buttonCreate, grid, usersBeanItemContainer),buttonClose(buttonClose));
//
//                        // Добавляем Layout содержащий кнопки и поля.
//                        verticalLayoutMainForm.addComponents(userFormContent,horizontalLayoutForButton);
//                        verticalLayoutMainForm.setComponentAlignment(userFormContent, Alignment.MIDDLE_CENTER);
//                        verticalLayoutMainForm.setComponentAlignment(horizontalLayoutForButton, Alignment.MIDDLE_CENTER);
//                }
//                else if (nameButton.equals("обновить") && user != null) {
//                        // Create the first field which will be focused
//                        firstName.setDescription("Укажите имя");
////                        firstName.addValidator(new StringLengthValidator("Недостаточно символов",3,10,false));
//                        firstName.setNullRepresentation("");
//                        firstName.setNullSettingAllowed(true);
//                        firstName.setValue(user.getItemProperty("firstName").getValue().toString());
//                        firstName.setWidth(100, Unit.PERCENTAGE);
//                        firstName.setIcon(FontAwesome.USER);
//                        firstName.setTabIndex(1);
//                        firstName.focus(); // Set focus to the user name
//
//                        lastName.setDescription("Укажите фамилию");
//                        lastName.setValue(user.getItemProperty("lastName").getValue().toString());
//                        lastName.setWidth(100, Unit.PERCENTAGE);
//                        lastName.setIcon(FontAwesome.USER);
//                        lastName.setTabIndex(2);
//
//                        otchestvo.setDescription("Укажите отчество");
//                        otchestvo.setValue(user.getItemProperty("otchestvo").getValue().toString());
//                        otchestvo.setWidth(100, Unit.PERCENTAGE);
//                        otchestvo.setIcon(FontAwesome.USER);
//                        otchestvo.setTabIndex(3);
//
//                        email.setDescription("Укажите email");
//                        email.setValue(user.getItemProperty("email").getValue().toString());
//                        email.setWidth(100, Unit.PERCENTAGE);
//                        email.setIcon(FontAwesome.INBOX);
//                        email.setTabIndex(4);
//
//                        telephone.setDescription("Укажите телефон");
//                        telephone.setValue(user.getItemProperty("telephone").getValue().toString());
//                        telephone.setWidth(100, Unit.PERCENTAGE);
//                        telephone.setIcon(FontAwesome.PHONE);
//                        telephone.setTabIndex(5);
//
//                        pol.setDescription("Укажите пол");
//                        pol.setValue(user.getItemProperty("pol").getValue().toString());
//                        pol.setWidth(100, Unit.PERCENTAGE);
//                        pol.setIcon(FontAwesome.MALE);
//                        pol.addItems("Мужской", "Женский");
////                        pol.select("Мужской");
//                        pol.setValue(user.getItemProperty("pol").getValue().toString());
//                        pol.setTextInputAllowed(false);
//                        pol.setNullSelectionAllowed(false);
//                        pol.setTabIndex(6);
//
//                        //Дата рождения. PopUp
//                        popupDateField.setDescription("Укажите дату рождения");
//                        try {
//                                System.out.println("Дата");
//                                date = (Date) user.getItemProperty("birthday").getValue();
//
//                        } catch (Exception e) {
//                                System.out.println(e.getStackTrace());
//                        }
//                        popupDateField.setValue(date);
//                        popupDateField.setWidth(100, Unit.PERCENTAGE);
//                        popupDateField.setIcon(FontAwesome.BIRTHDAY_CAKE);
//
//
//                        popupDateField.setDateFormat("dd-MM-yyyy");
//                        popupDateField.setTextFieldEnabled(false);
//                        popupDateField.setBuffered(true);
//                        popupDateField.setLocale(new Locale("ru"));
//
//
//                        // Добавляем Field на форму.
//                        userFormContent.addComponents(firstName,lastName,otchestvo,email,telephone,pol,popupDateField);
//
//
//                        // Layout отвечающий за кнопки.
//                        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
//                        horizontalLayoutForButton.setWidth(100,Unit.PERCENTAGE);
//                        horizontalLayoutForButton.setSpacing(true);
//                        horizontalLayoutForButton.addComponents(updateButton(buttonCreate, grid, usersBeanItemContainer, user, itemId),buttonClose(buttonClose));
//
//                        // Добавляем Layout содержащий кнопки и поля.
//                        verticalLayoutMainForm.addComponents(userFormContent,horizontalLayoutForButton);
//                        verticalLayoutMainForm.setComponentAlignment(userFormContent, Alignment.MIDDLE_CENTER);
//                        verticalLayoutMainForm.setComponentAlignment(horizontalLayoutForButton, Alignment.MIDDLE_CENTER);
//
//
//                }
//
//                return verticalLayoutMainForm;
//        }
//
//
//        public Button buttonClose(Button buttonClose) {
//                buttonClose = new Button("Закрыть",FontAwesome.CLOSE);
//                buttonClose.setWidth(100,Unit.PERCENTAGE);
//                buttonClose.addClickListener(new Button.ClickListener() {
//                        public void buttonClick(Button.ClickEvent event) {
//                                close(); // Close the sub-window
//                        }
//                });
//                return buttonClose;
//
//        }
//
//        public Button buttonCreateUser(Button addButtonUser, Grid grid, BeanItemContainer<Users> usersBeanItemContainer) {
//                addButtonUser = new Button("Добавить",FontAwesome.CHECK);
//                addButtonUser.setWidth(100,Unit.PERCENTAGE);
//                addButtonUser.addClickListener(new Button.ClickListener() {
//                        @SneakyThrows
//                        @Override
//                        public void buttonClick(Button.ClickEvent clickEvent) {
//                                usersBeanItemContainer.addBean(
//                                        new Users(firstName.getValue(),lastName.getValue(),otchestvo.getValue(),email.getValue(),telephone.getValue(),pol.getValue().toString(),popupDateField.getValue()
//                                        ));
//                                close();
//                                Notification.show("Пользователь добавлен!");
//                                grid.getSelectionModel().reset();
//
//                        }
//                });
//                return  addButtonUser;
//        }
//
//        public Button updateButton(Button updateButton, Grid grid, BeanItemContainer<Users> usersBeanItemContainer,Item user, Object itemId) {
//                updateButton = new Button("Изменить",FontAwesome.CHECK);
//                updateButton.setWidth(100,Unit.PERCENTAGE);
//                updateButton.addClickListener(new Button.ClickListener() {
//                        @SneakyThrows
//                        @Override
//                        public void buttonClick(Button.ClickEvent clickEvent) {
//                                System.out.println("Изменить имя");
//                                usersBeanItemContainer.removeItem(itemId);
//                                user.getItemProperty("firstName").setValue(firstName.getValue());
//                                close();
////                                grid.getSelectionModel().reset();
//                                Notification.show("Пользователь изменен!");
//
//
//                        }
//                });
//                return  updateButton;
//        }

//}