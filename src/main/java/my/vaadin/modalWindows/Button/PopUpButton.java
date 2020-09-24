package my.vaadin.modalWindows.Button;

import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PopUpButton  extends Button {

    // Конструктор кнопки с иконками.
    public PopUpButton(String caption,Integer width,FontAwesome fontAwesome) {
        super(caption);
        this.setIcon(fontAwesome); // Иконка.
        this.setWidth(width,Unit.PERCENTAGE);
    }
    public PopUpButton(String caption,Integer width,String description ,FontAwesome fontAwesome) {
        this(caption, width, fontAwesome);
        this.setDescription(description);
    }


}
