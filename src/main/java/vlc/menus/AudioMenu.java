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

public class AudioMenu extends myMenu {
    public AudioMenu(Stage parentStage, BorderPane rootPane, Player player, ToolBar parentToolbar) {
        menu = new Menu("_Audio");
        ArrayList<myMenuItem> menuItemList = new ArrayList<>();

        EventHandler<ActionEvent> audioTrackEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Audio Track", "/audio_track.png", audioTrackEventHandler));

        EventHandler<ActionEvent> volumeUpEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Audio Device", "/audio_device.png", volumeUpEventHandler));

        // Stereo Mode: Create the speed submenu
        // will be overwritten by subMenu, but needed du to if instruction line #67
        menuItemList.add(new myMenuItem("_Stereo", "", null));
        // Submenu creation
        Menu stereoSubMenu = new Menu("_Stereo");
        ArrayList<myMenuItem> stereoSubmenuItems = new ArrayList<>();
        stereoSubmenuItems.add(new myMenuItem("Mono", "/faster.png", null)); // playback speed *= 1.5
        stereoSubmenuItems.add(new myMenuItem("Stereo", "/faster_fine.png", null)); // playback speed *= 1.2
        stereoSubmenuItems.add(new myMenuItem("Left", "/normal_speed.png", null)); // playback speed = normal
        stereoSubmenuItems.add(new myMenuItem("Right", "/slower.png", null)); // playback speed /= 1.5
        stereoSubmenuItems.add(new myMenuItem("Reverse Stereo", "/slower_fine.png", null)); // playback speed /= 1.2

        for (myMenuItem item: stereoSubmenuItems) {
            ImageView view = new ImageView(new Image(item.getIcon())); // get the image
            view.setFitHeight(16); // scale to 16 x 16
            view.setFitWidth(16);
            // Create a Menuitem
            MenuItem menuItem = new MenuItem(item.getName(), view);
            // Attach the event Handler present inside myMenuItem
            EventHandler<ActionEvent> handler = item.getActionHandler();
            if (handler != null) {
                menuItem.addEventHandler(ActionEvent.ACTION, handler);
            }
            // Finally add the MenuItem in the speed subMenu
            stereoSubMenu.getItems().add(menuItem);
        }

        // Visualizations of sound
        // will be overwritten by subMenu, but needed du to if instruction line #67
        menuItemList.add(new myMenuItem("_Visualizations", "", null));
        // Submenu creation
        Menu visualizationsSubMenu = new Menu("_Visualizations");
        // Null Event Handler to attach to submenu items waiting for the implementation
        EventHandler<ActionEvent> nullEventHandler = actionEvent -> {
            System.out.println("To implement");
        };

        ArrayList<myMenuItem> visualizationsSubmenuItems = new ArrayList<>();
        visualizationsSubmenuItems.add(new myMenuItem("Disable", "/stop.png", nullEventHandler));
        visualizationsSubmenuItems.add(new myMenuItem("Spectrum", "/spectrum.png", nullEventHandler));
        visualizationsSubmenuItems.add(new myMenuItem("Scope", "/scope.png", nullEventHandler));
        visualizationsSubmenuItems.add(new myMenuItem("Goom", "/gloom.png", nullEventHandler));
        visualizationsSubmenuItems.add(new myMenuItem("ProjectM", "/projectm.png", nullEventHandler));
        visualizationsSubmenuItems.add(new myMenuItem("VU Meter", "/vu_meter.png", nullEventHandler));
        visualizationsSubmenuItems.add(new myMenuItem("3D Spectrum", "/3d_spectrum.png", nullEventHandler));

        for (myMenuItem item: visualizationsSubmenuItems) {
            ImageView view = new ImageView(new Image(item.getIcon())); // get the image
            view.setFitHeight(16); // scale to 16 x 16
            view.setFitWidth(16);
            // Create a Menuitem
            MenuItem menuItem = new MenuItem(item.getName(), view);
            // Attach the event Handler present inside myMenuItem
            EventHandler<ActionEvent> handler = item.getActionHandler();
            if (handler != null) {
                menuItem.addEventHandler(ActionEvent.ACTION, handler);
            }
            // Finally add the MenuItem in the speed subMenu
            visualizationsSubMenu.getItems().add(menuItem);
        }

        EventHandler<ActionEvent> volumeDownEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Volume Down", "/volume_down.png", volumeDownEventHandler));

        EventHandler<ActionEvent> muteEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Volume Mute", "/volume_mute.png", muteEventHandler));

        // add menus to javafx menu class
        for (myMenuItem item: menuItemList) {
            if (item.getName() == "_Stereo") {
                menu.getItems().add(stereoSubMenu); // specific: stereo is a submenu, not a menu item
            } else if (item.getName() == "_Visualizations") {
                menu.getItems().add(visualizationsSubMenu); // specific: stereo is a submenu, not a menu item
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

        // add my toolbar icons in the parent Toolbar given as argument of this constructor
        // Create the buttons
        Button toolbarUpIcon = myToolbarIcon.buildToolBarIcon("Volume UP", "/volume_up.png", volumeUpEventHandler);
        Button toolbarDownIcon = myToolbarIcon.buildToolBarIcon("Volume Down", "/volume_down.png", volumeDownEventHandler);
        Button toolbarMuteIcon = myToolbarIcon.buildToolBarIcon("Mute", "/volume_mute.png", muteEventHandler);

        // Then insert into the toolbar given as argument
        parentToolbar.getItems().add(toolbarUpIcon);
        parentToolbar.getItems().add(toolbarDownIcon);
        parentToolbar.getItems().add(toolbarMuteIcon);
    }

    public Menu getMenu() {
        return this.menu;
    }
}
