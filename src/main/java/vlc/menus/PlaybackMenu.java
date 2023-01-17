package vlc.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vlc.Player;

import java.util.ArrayList;

public class PlaybackMenu extends myMenu {
    public PlaybackMenu(Stage parentStage, BorderPane rootPane, Player player, ToolBar parentToolbar) {
        menu = new Menu("_Playback");
        ArrayList<myMenuItem> menuItemList = new ArrayList<>();

        EventHandler<ActionEvent> playEventHandler = actionEvent -> {
            if (player != null) {
                try {
                    if (player != null) {
                        player.play();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        menuItemList.add(new myMenuItem("_Play", "/play.png", playEventHandler));

        EventHandler<ActionEvent> stopEventHandler = actionEvent -> {
            if (player != null) {
                try {
                    if (player != null) {
                        player.stop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        menuItemList.add(new myMenuItem("_Stop", "/stop.png", stopEventHandler));

        EventHandler<ActionEvent> previousEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Previous", "/prev.png", previousEventHandler));

        EventHandler<ActionEvent> nextEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Next", "/next.png", nextEventHandler));

        EventHandler<ActionEvent> recordEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Record", "/record.png", recordEventHandler));

        EventHandler<ActionEvent> speedEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        // Create the speed submenu
        Menu speedSubMenu = new Menu("_Speed");
        ArrayList<myMenuItem> speedSubmenuItems = new ArrayList<>();
        speedSubmenuItems.add(new myMenuItem("Faster", "/faster.png", null)); // playback speed *= 1.5
        speedSubmenuItems.add(new myMenuItem("Faster Fine", "/faster_fine.png", null)); // playback speed *= 1.2
        speedSubmenuItems.add(new myMenuItem("Normal Speed", "/normal_speed.png", null)); // playback speed = normal
        speedSubmenuItems.add(new myMenuItem("Slower", "/slower.png", null)); // playback speed /= 1.5
        speedSubmenuItems.add(new myMenuItem("Slower Fine", "/slower_fine.png", null)); // playback speed /= 1.2

        for (myMenuItem item: speedSubmenuItems) {
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
            speedSubMenu.getItems().add(menuItem);
        }

        // will be replaced by submenu (to correct !)
        menuItemList.add(new myMenuItem("_Speed", "/speed.png", speedEventHandler));

        EventHandler<ActionEvent> chapterEventHandler = actionEvent -> {
            System.out.println("To implement");
        };
        menuItemList.add(new myMenuItem("_Chapter", "/chapter.png", chapterEventHandler));

        // add menus to javafx menu class
        for (myMenuItem item: menuItemList) {
            if (item.getName() == "_Speed") { // submenu !!
                menu.getItems().add(speedSubMenu);
            } else {
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
                // Finally add the MenuItem in the Menu
                menu.getItems().add(menuItem);
            }
        }

        // add my toolbar icons in the parent Toolbar given as argument of this constructor
        // Create the buttons
        Button toolbarNextIcon = myToolbarIcon.buildToolBarIcon("Next Track", "/next.png", nextEventHandler);
        Button toolbarPrevIcon = myToolbarIcon.buildToolBarIcon("Previous Track", "/prev.png", previousEventHandler);
        Button toolbarPlayIcon = myToolbarIcon.buildToolBarIcon("Play", "/play.png", playEventHandler);
        Button toolbarStopIcon = myToolbarIcon.buildToolBarIcon("Stop", "/stop.png", stopEventHandler);
        Button toolbarRecordIcon = myToolbarIcon.buildToolBarIcon("Record", "/record.png", recordEventHandler);

        // Then insert into the toolbar given as argument
        parentToolbar.getItems().add(toolbarNextIcon);
        parentToolbar.getItems().add(toolbarPrevIcon);
        parentToolbar.getItems().add(toolbarPlayIcon);
        parentToolbar.getItems().add(toolbarStopIcon);
        parentToolbar.getItems().add(toolbarRecordIcon);
    }

    public Menu getMenu() {
        return this.menu;
    }
}
