package vlc;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.*;
import org.controlsfx.control.action.Action;

public class MediaBar extends HBox {
    Slider time = new Slider();
    Slider vol = new Slider();
    Button playButton = new Button("||");
    Label volume = new Label("Volume: ");
    MediaPlayer player;

    public MediaBar(MediaPlayer player_) {
        player = player_;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));
        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);
        HBox.setHgrow(time, Priority.ALWAYS);
        playButton.setPrefHeight(30);

        // Adding the components to the bottom
        getChildren().add(playButton);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);

        // Adding functionality
        // to play the media player
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MediaPlayer.Status status = player.getStatus();
                if (status == status.PLAYING) {
                    if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
                        player.seek(player.getStartTime());
                        player.play();
                    } else {
                        player.pause();
                        playButton.setText(">");
                    }
                }
                if (status == MediaPlayer.Status.HALTED ||
                        status == MediaPlayer.Status.STOPPED ||
                        status == MediaPlayer.Status.PAUSED) {
                    player.play();
                    playButton.setText("||");
                }
            }
        });
        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updatesValues();
            }
        });

        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (time.isPressed()) {
                    player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
                }
            }
        });

        vol.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (vol.isPressed()) {
                    player.setVolume(vol.getValue() / 100);
                }
            }
        });
    }

    protected void updatesValues() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                // Updating to the new time value
                // This will move the slider while running your video
                time.setValue(player.getCurrentTime().toMillis() * player.getTotalDuration().toMillis() * 100);
            }
        });
    }
}
