package vlc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
// Menus
import vlc.menus.AudioMenu;
import vlc.menus.MediaMenu;
import vlc.menus.PlaybackMenu;
import vlc.menus.VideoMenu;

import java.io.IOException;
import java.util.HashMap;

public class App extends Application {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private Button button;
    private Label label;

    private Player player = null;
    HashMap<String, MenuItem> mediaMenuHashMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        */
        this.stage = primaryStage;
        this.stage.setTitle("Event Explorer");
        this.root = new BorderPane();
        this.scene = new Scene(root, 800, 600, Color.DODGERBLUE);
        this.button = new Button("Click Me");
        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                label.setText("Button Clicked");
            }
        });
        this.label = new Label();
        this.root.setCenter(button);
        this.root.setCenter(label);

        // event filter for root
        /*
        root.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Event filter");
                actionEvent.consume();
            }
        });
        */
        // event handler for root
        root.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Event handler");
            }
        });

        //init player
        this.player = new Player();

        // Menu construction
        // add the toolbar first as it is needed
        ToolBar toolbar = new ToolBar();

        // Media Menu
        MediaMenu mediaMenu = new MediaMenu(this.stage, this.root, this.player, toolbar);

        // Playback Menu
        PlaybackMenu playbackMenu = new PlaybackMenu(this.stage, this.root, this.player, toolbar);

        // Audio Menu
        AudioMenu audioMenu = new AudioMenu(this.stage, this.root, this.player, toolbar);

        // video Menu
        VideoMenu videoMenu = new VideoMenu(this.stage, this.root, this.player, toolbar);

        // add all menus in menubar and toolbar (inside the classes)
        MenuBar menubar = new MenuBar();
        menubar.getMenus().add(mediaMenu.getMenu());
        menubar.getMenus().add(playbackMenu.getMenu());
        menubar.getMenus().add(audioMenu.getMenu());
        menubar.getMenus().add(videoMenu.getMenu());

        // add Menu bar
        VBox top_vbox = new VBox();
        top_vbox.getChildren().add(menubar);

        // toolbar is filled inside mediaMenu, playbackMenu etc ...
        top_vbox.getChildren().add(toolbar);

        root.setTop(top_vbox);

        this.stage.setScene(scene);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}