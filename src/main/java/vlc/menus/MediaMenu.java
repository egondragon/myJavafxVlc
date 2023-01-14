package vlc.menus;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vlc.Player;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class MediaMenu {
    Menu menu;
    public MediaMenu(Stage parentStage, BorderPane rootPane, Player player, ToolBar parentToolbar) {
        menu = new Menu("_Media");
        ArrayList<myMenuItem> menuItemList = new ArrayList<>();

        EventHandler<ActionEvent> openEventHandler = actionEvent -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Enter media file name");
            File mediaToOpen = chooser.showOpenDialog(parentStage.getOwner());
            // System.out.println("filepath " + mediaToOpen.getAbsolutePath());
            // Try to play the file
            if (mediaToOpen != null) {
                // System.out.println(mediaToOpen.getAbsolutePath());
                try {
                    if (player != null) {
                        player.stop();
                        player.initPlayer(mediaToOpen.toURI().toURL().toExternalForm());
                        rootPane.setCenter(player);
                        player.play();
                    }
                } catch (MalformedURLException event) {
                    event.printStackTrace();
                }
            }
        };

        menuItemList.add(new myMenuItem("_Open Media", "/open_media.png", openEventHandler));

        /* Open folder will list all files and display them as a tree */
        EventHandler<ActionEvent> openFolderHandler = ActionEvent -> {
                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle("Open Media folder");
                File defaultDirectory = new File("c:/users/jpbz7878/videos");
                chooser.setInitialDirectory(defaultDirectory);
                File selectedFolder = chooser.showDialog(parentStage);
                // show a tree with all files
                ImageView view = new ImageView(new Image(getClass().getResourceAsStream("/folder.png")));
                view.setFitHeight(16);
                view.setFitWidth(16);
                TreeItem<String> rootFolder = new TreeItem<String>("root", view);
                rootFolder.setExpanded(true);
                // List all the files
                File[] fileList = selectedFolder.listFiles(new FilenameFilter() {
                    public boolean accept (File dir, String sz_name) {
                        return sz_name.toLowerCase().endsWith(".mp4");
                    }
                });
                System.out.println(selectedFolder.getAbsolutePath());

                // add them in the tree structure

                if (fileList != null && fileList.length > 1) {
                    for (File f: fileList) {
                        System.out.println(f.getAbsolutePath());

                        view = new ImageView(new Image(getClass().getResourceAsStream("/open_media.png")));
                        view.setFitHeight(16);
                        view.setFitWidth(16);
                        TreeItem<String> item = new TreeItem<>(f.getName(),  view);
                        rootFolder.getChildren().add(item);
                    }
                }

                TreeView<String> treeView = new TreeView<>(rootFolder);
                rootPane.setLeft(treeView);

        };
        menuItemList.add(new myMenuItem("_Open Folder", "folder.png", openFolderHandler));
        menuItemList.add(new myMenuItem("_Open Disc", "/disc.png"));
        menuItemList.add(new myMenuItem("_Open Network Stream", "/open_network_stream.png"));
        menuItemList.add(new myMenuItem("_Save current playlist", "/save_media.png"));
        menuItemList.add(new myMenuItem("_Convert Media", "/convert.png"));

        // Quit event handler (caution, this is a lambda !!)
        EventHandler<ActionEvent> quitEventHandler = actionEvent -> {
            System.exit(0);
        };

        menuItemList.add(new myMenuItem("_Quit", "exit.png", quitEventHandler));

        for (myMenuItem item: menuItemList) {
            ImageView view = new ImageView(new Image(item.getIcon()));
            view.setFitHeight(16);
            view.setFitWidth(16);
            MenuItem menuItem = new MenuItem(item.getName(), view);
            EventHandler<ActionEvent> handler = item.getActionHandler();
            if (handler != null) {
                menuItem.addEventHandler(ActionEvent.ACTION, handler);
            }
            menu.getItems().add(menuItem);
        }

        // add my toolbar icons in the parent Toolbar given as argument of this constructor
        Button toolbarOpenIcon = myToolbarIcon.buildToolBarIcon("Open Media", "/open_media.png", openEventHandler);
        Button toolbarFolderIcon = myToolbarIcon.buildToolBarIcon("Open Folder", "/folder.png", openFolderHandler);
        Button toolbarNetworkIcon = myToolbarIcon.buildToolBarIcon("Open Network Stream", "/open_network_stream.png", null);
        Button toolbarExitIcon = myToolbarIcon.buildToolBarIcon("Exit", "/exit.png", quitEventHandler);

        parentToolbar.getItems().add(toolbarOpenIcon);
        parentToolbar.getItems().add(toolbarFolderIcon);
        parentToolbar.getItems().add(toolbarNetworkIcon);
        parentToolbar.getItems().add(toolbarExitIcon);

    }

    public Menu getMenu() {
        return this.menu;
    }
}
