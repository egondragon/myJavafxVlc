package vlc.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class myMenuItem {
    private String sz_name;
    private String sz_icon;

    private EventHandler<ActionEvent> actionHandler = null;

    public myMenuItem(String sz_name, String sz_icon) {
        this.sz_name = sz_name;
        this.sz_icon = sz_icon;
    }

    public myMenuItem(String sz_name, String sz_icon, EventHandler<ActionEvent> handler) {
        this.sz_name = sz_name;
        this.sz_icon = sz_icon;
        this.actionHandler = handler;
    }

    public String getName() {
        return sz_name;
    }

    public String getIcon() {
        return sz_icon;
    }

    public EventHandler<ActionEvent> getActionHandler() {
        return actionHandler;
    }
}
