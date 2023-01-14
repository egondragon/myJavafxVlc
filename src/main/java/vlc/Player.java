package vlc;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.*;

public class Player extends BorderPane {
    Media media;
    MediaPlayer player = null;
    MediaView view;
    Pane mpane;
    MediaBar bar;

    public void initPlayer(String sz_file) {
        Media media = new Media(sz_file);
        player = new MediaPlayer(media);
        view = new MediaView(player);
        mpane = new Pane();
        mpane.getChildren().add(view);
        setCenter(mpane);
        bar = new MediaBar(player);

        setBottom(bar);
        setStyle("-fx-background-color:#bfc2c7");
    }
    public void play() {
        if (player != null) {
            player.play();
        }
    }

    public void stop() {
        if (player != null) {
            player.stop();
        }
    }

}
