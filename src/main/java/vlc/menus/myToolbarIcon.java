package vlc.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class myToolbarIcon {
    public static Button buildToolBarIcon(String sz_iconName, String sz_icon, EventHandler<ActionEvent> eventHandler) {
        ImageView view = new ImageView(new Image(sz_icon));
        view.setFitHeight(24);
        view.setFitWidth(24);
        Button toolbarIcon = new Button("", view);

        /* Caution: used by mediaMenu */
        if (eventHandler != null) {
            toolbarIcon.setOnAction(eventHandler);
        }
        toolbarIcon.setTooltip(new Tooltip(sz_iconName));
        return toolbarIcon;
    }


}
