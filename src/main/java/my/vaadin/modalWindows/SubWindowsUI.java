package my.vaadin.modalWindows;
import com.vaadin.ui.*;

public class SubWindowsUI extends Window {
        public SubWindowsUI() {

                super("Модальное окно"); //Заголовок.
                // Размер окна.
                this.setHeight("200px");
                this.setWidth("400px");
                center();

                //Базовое содержимое окна.
                VerticalLayout mainLayout = new VerticalLayout(); // Создаем вертикальный Layout
//                mainLayout.addComponents(new Label("Текст в модальном окне"), new Button("Кнопка"));
                mainLayout.setMargin(true); // Вшеншие отствпы.
                setContent(mainLayout); //Для отображение элементов в Layout

                // Кноака закрытия.
                setClosable(true);

                // Кнопка закрытия модального окна.
                Button ok = new Button("OK");
                ok.addClickListener(new Button.ClickListener() {
                        public void buttonClick(Button.ClickEvent event) {
                                close(); // Close the sub-window
                        }
                });
                mainLayout.addComponent(ok);


        }
}
