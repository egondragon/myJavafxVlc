package vlc.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vlc.Player;

import java.util.ArrayList;

public class VideoMenu extends myMenu {

    private Menu buildSubMenus(String submenuName, ArrayList<myMenuItem> submenuItemList) {
        Menu submenu = new Menu(submenuName);
        for (myMenuItem item: submenuItemList) {
            MenuItem menuItem;
            String sz_iconPath =item.getIcon();
            if (sz_iconPath != null && sz_iconPath != "") {
                ImageView view = new ImageView(new Image(sz_iconPath)); // get the image
                view.setFitHeight(16); // scale to 16 x 16
                view.setFitWidth(16);
                // Create a Menuitem with image
                menuItem = new MenuItem(item.getName(), view);
            } else { // no icon
                menuItem = new MenuItem(item.getName());
            }
            // Attach the event Handler present inside myMenuItem
            EventHandler<ActionEvent> handler = item.getActionHandler();
            if (handler != null) {
                menuItem.addEventHandler(ActionEvent.ACTION, handler);
            }
            // Finally add the MenuItem in the speed subMenu
            submenu.getItems().add(menuItem);
        }
        return submenu;
    }

    public VideoMenu(Stage parentStage, BorderPane rootPane, Player player, ToolBar parentToolbar) {
        menu = new Menu("_Video");
        ArrayList<myMenuItem> menuItemList = new ArrayList<>();

        EventHandler<ActionEvent> VideoTrackEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Video Track", "/video_track.png", VideoTrackEventHandler));

        EventHandler<ActionEvent> volumeUpEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Fullscreen", "/fullscreen.png", volumeUpEventHandler));

        // Zoom: Create the speed submenu
        // will be overwritten by subMenu, but needed du to if instruction line #67
        menuItemList.add(new myMenuItem("_Zoom", "", null));
        // Submenu creation
        ArrayList<myMenuItem> zoomSubmenuItems = new ArrayList<>();
        zoomSubmenuItems.add(new myMenuItem("Quarter", "/zoom_quarter.png", null)); // playback speed *= 1.5
        zoomSubmenuItems.add(new myMenuItem("Half", "/zoom_half.png", null)); // playback speed *= 1.2
        zoomSubmenuItems.add(new myMenuItem("Normal", "/zoom_normal.png", null)); // playback speed = normal
        zoomSubmenuItems.add(new myMenuItem("Double", "/zoom_double.png", null)); // playback speed /= 1.5
        /* insert all submenus items in the submenus */
        Menu zoomSubMenu = buildSubMenus("_Zoom", zoomSubmenuItems);

        // Aspect Ratio submenus
        menuItemList.add(new myMenuItem("_Aspect Ratio", "", null));
        // Submenu creation
        ArrayList<myMenuItem> aspectRatioSubmenuItems = new ArrayList<>();
        // 16/01/2023: Event Handler will be coded later
        aspectRatioSubmenuItems.add(new myMenuItem("16:9", null, null)); // playback speed *= 1.5
        aspectRatioSubmenuItems.add(new myMenuItem("4:3", null, null)); // playback speed *= 1.2
        aspectRatioSubmenuItems.add(new myMenuItem("1:1", null, null)); // playback speed = normal
        aspectRatioSubmenuItems.add(new myMenuItem("16:10", null, null)); // playback speed /= 1.5
        aspectRatioSubmenuItems.add(new myMenuItem("5:4", null, null)); // playback speed /= 1.5

        Menu aspectRatioSubMenu = buildSubMenus("_Aspect Ratio", aspectRatioSubmenuItems);

        // Crop submenus
        menuItemList.add(new myMenuItem("_Crop", "", null));
        // Submenu creation
        ArrayList<myMenuItem> cropSubmenuItems = new ArrayList<>();
        // 16/01/2023: Event Handler will be coded later
        cropSubmenuItems.add(new myMenuItem("16:9", null, null)); // playback speed *= 1.5
        cropSubmenuItems.add(new myMenuItem("4:3", null, null)); // playback speed *= 1.2
        cropSubmenuItems.add(new myMenuItem("5:3", null, null)); // playback speed = normal
        cropSubmenuItems.add(new myMenuItem("5:4", null, null)); // playback speed = normal
        cropSubmenuItems.add(new myMenuItem("1:1", null, null)); // playback speed = normal
        cropSubmenuItems.add(new myMenuItem("16:10", null, null)); // playback speed /= 1.5
        cropSubmenuItems.add(new myMenuItem("1:85:1", null, null)); // playback speed /= 1.5

        Menu cropSubMenu = buildSubMenus("_Crop", cropSubmenuItems);

        // DeInterlace
        // will be overwritten by subMenu, but needed du to if instruction line #67
        menuItemList.add(new myMenuItem("_DeInterlace", "", null));
        // Submenu creation
        // Null Event Handler to attach to submenu items waiting for the implementation
        EventHandler<ActionEvent> nullEventHandler = actionEvent -> {
            System.out.println("To implement");
        };

        ArrayList<myMenuItem> deinterlaceSubmenuItems = new ArrayList<>();
        deinterlaceSubmenuItems.add(new myMenuItem("Auto", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("Discard", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("Blend", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("Mean", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("Bob", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("Linear", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("X", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("Yadif", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("Yadif 2X", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("Phosphor", "", nullEventHandler));
        deinterlaceSubmenuItems.add(new myMenuItem("NTSC", "", nullEventHandler));

        Menu deinterlaceSubMenu = buildSubMenus("DeInterlace", deinterlaceSubmenuItems);

        EventHandler<ActionEvent> volumeDownEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Take Snapshot", "/snapshot.png", volumeDownEventHandler));

        // add menus to javafx menu class
        for (myMenuItem item: menuItemList) {
            if (item.getName() == "_zoom") {
                menu.getItems().add(zoomSubMenu); // specific: zoom is a submenu, not a menu item
            } else if (item.getName() == "_DeInterlace") {
                menu.getItems().add(deinterlaceSubMenu); // specific: zoom is a submenu, not a menu item
            } else if (item.getName() == "_Crop") {
                menu.getItems().add(cropSubMenu); // specific: zoom is a submenu, not a menu item
            } else if (item.getName() == "_Aspect Ratio") {
                menu.getItems().add(aspectRatioSubMenu); // specific: zoom is a submenu, not a menu item
            } else {
                MenuItem menuItem;
                String sz_icon = item.getIcon();
                if (sz_icon != null && sz_icon.length() > 1) {
                    ImageView view = new ImageView(new Image(item.getIcon())); // get the image
                    view.setFitHeight(16); // scale to 16 x 16
                    view.setFitWidth(16);
                    // Create a Menuitem
                    menuItem = new MenuItem(item.getName(), view);
                } else { // no icon
                    menuItem = new MenuItem(item.getName());
                }
                // Attach the event Handler present inside myMenuItem
                EventHandler<ActionEvent> handler = item.getActionHandler();
                if (handler != null) {
                    menuItem.addEventHandler(ActionEvent.ACTION, handler);
                }
                // Finally add the MenuItem in the Menu
                menu.getItems().add(menuItem);
            }
        }
        // No Toolbar items for now
    }

    public Menu getMenu() {
        return this.menu;
    }
}
