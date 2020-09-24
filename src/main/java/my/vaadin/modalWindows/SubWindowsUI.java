package my.vaadin.modalWindows;
import com.vaadin.data.Item;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;


import java.util.Date;
import java.util.Locale;

public class SubWindowsUI extends Window {
        Button buttonClose;
        Button buttonCreate;

        public SubWindowsUI(String caption, Item users) {


                super(caption); //Заголовок.
                // Размер окна.
                System.out.println(users);
//                System.out.println(users.getItemProperty("firstName").getValue());
                this.setHeight("520");
                this.setWidth("480");
                this.setModal(true); // Указываем, что окно должно быть модальное, данная настройка блокирует задний фон.
                this.setResizable(false);  // Запрет на растягивание окна.
                this.setDraggable(false); // Запрет на перестаскивание окна.
                center();

                //Базовое содержимое окна.
                VerticalLayout mainLayout = new VerticalLayout(); // Создаем вертикальный Layout
                mainLayout.setMargin(true); // Вшеншие отступы.
                setClosable(true);// Кнопка закрытия.
                setContent(mainLayout); //Для отображение элементов в Layout


                VerticalLayout crateFormLayout = crateFormLayout(users);
                mainLayout.addComponent(crateFormLayout);
                mainLayout.setComponentAlignment(crateFormLayout, Alignment.MIDDLE_CENTER);


        }
        /* Создание панели для ввода данных */
        public VerticalLayout crateFormLayout(Item users) {

                VerticalLayout verticalLayoutMainForm = new VerticalLayout();
                FormLayout userFormContent = new FormLayout();

                // Create the first field which will be focused
                final TextField firstName = new TextField("Имя");
                firstName.focus(); // Set focus to the user name
                firstName.setWidth(100,Unit.PERCENTAGE);
                firstName.setIcon(FontAwesome.USER);
                firstName.setTabIndex(1);


                final TextField lastName = new TextField("Фамилия");
                lastName.setIcon(FontAwesome.USER);
                lastName.setWidth(100,Unit.PERCENTAGE);
                lastName.setTabIndex(2);

                final TextField otchestvo = new TextField("Отчество");
                otchestvo.setIcon(FontAwesome.USER);
                otchestvo.setWidth(100,Unit.PERCENTAGE);
                otchestvo.setTabIndex(3);

                final TextField email = new TextField("Почта");
                email.setIcon(FontAwesome.INBOX);
                email.setWidth(100,Unit.PERCENTAGE);
                email.setTabIndex(4);

                final TextField telephone = new TextField("Телефон");
                telephone.setIcon(FontAwesome.PHONE);
                telephone.setWidth(100,Unit.PERCENTAGE);
                telephone.setTabIndex(5);

                final ComboBox pol = new ComboBox("Пол");
                pol.setIcon(FontAwesome.MALE);
                pol.setWidth(100,Unit.PERCENTAGE);
                pol.addItems("Мужской","Женский");
                pol.select("Мужской");
                pol.setTextInputAllowed(false);
                pol.setNullSelectionAllowed(false);

                //Дата рождения. PopUp
                PopupDateField popupDateField  = new PopupDateField("Дата рождения");
                popupDateField.setWidth(100,Unit.PERCENTAGE);
                popupDateField.setIcon(FontAwesome.BIRTHDAY_CAKE);
                popupDateField.setTextFieldEnabled(false);
                popupDateField.setValue(new Date());
                popupDateField.setDateFormat("dd-MM-yyyy");
                popupDateField.setBuffered(true);
                popupDateField.setLocale(new Locale("ru"));


                // Layout отвечающий за кнопки.
                HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
                horizontalLayoutForButton.setWidth(100,Unit.PERCENTAGE);
                horizontalLayoutForButton.setSpacing(true);

                if (users == null) {
                        System.out.println("Кнопка  (закрыть) - (добавить)");
                        buttonClose = new Button("Закрыть",FontAwesome.CLOSE);
                        buttonClose.setWidth(100,Unit.PERCENTAGE);
                        buttonClose(buttonClose);

                        buttonCreate = new Button("Добавить", FontAwesome.CHECK);
                        buttonCreate.setWidth(100,Unit.PERCENTAGE);
                        buttonCreateUser(buttonCreate);
                        horizontalLayoutForButton.addComponents(buttonCreate,buttonClose);
                        //Выравнивание.
                        horizontalLayoutForButton.setComponentAlignment(buttonCreate,Alignment.MIDDLE_CENTER);
                        horizontalLayoutForButton.setComponentAlignment(buttonClose,Alignment.MIDDLE_CENTER);
                }
                else {
                        System.out.println("Создание пользователя класс SupWindowsUI");
                        System.out.println("Кнопка  (закрыть)");
                        buttonClose = new Button("Закрыть",FontAwesome.CLOSE);
                        buttonClose.setWidth(100,Unit.PERCENTAGE);
                        buttonClose(buttonClose);
                        horizontalLayoutForButton.addComponent(buttonClose);
                        //Выравнивание.
                        horizontalLayoutForButton.setComponentAlignment(buttonClose,Alignment.MIDDLE_CENTER);

                }


                // Добавляем Field на форму.
                userFormContent.addComponents(firstName,lastName,otchestvo,email,telephone,pol,popupDateField);

                // Добавляем Layout содержащий кнопки и поля.
                verticalLayoutMainForm.addComponents(userFormContent,horizontalLayoutForButton);
                verticalLayoutMainForm.setComponentAlignment(userFormContent, Alignment.MIDDLE_CENTER);
                verticalLayoutMainForm.setComponentAlignment(horizontalLayoutForButton, Alignment.MIDDLE_CENTER);

                return verticalLayoutMainForm;
        }


        public void buttonClose(Button buttonClose) {
                buttonClose.addClickListener(new Button.ClickListener() {
                        public void buttonClick(Button.ClickEvent event) {
                                close(); // Close the sub-window
                        }
                });

        }

        public void buttonCreateUser(Button addButtonUser) {
                addButtonUser = new Button("Добавить", FontAwesome.CHECK);
                addButtonUser.setWidth(100,Unit.PERCENTAGE);
                addButtonUser.addClickListener(
                        new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
//                                // Not really necessary to remove the listeners
//                                for (Object l: firstName.getListeners(FieldEvents.BlurEvent.class))
//                                        firstName.removeBlurListener((FieldEvents.BlurListener) l);
//                                for (Object l: lastName.getListeners(FieldEvents.BlurEvent.class))
//                                        lastName.removeBlurListener((FieldEvents.BlurListener) l);

                                Notification.show("Bling!");
                        }
                });
        }

}
