module com.example.myjavafxvlc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;

    opens vlc to javafx.fxml;
    exports vlc;
}