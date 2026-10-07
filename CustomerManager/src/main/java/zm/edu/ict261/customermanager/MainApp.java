package zm.edu.ict261.customermanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/** Starts the JavaFX application and loads the View (FXML + CSS). */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("customer-view.fxml"));
        Scene scene = new Scene(loader.load(), 620, 560);
        scene.getStylesheets().add(Objects.requireNonNull(
                MainApp.class.getResource("style.css")).toExternalForm());

        stage.setTitle("Customer Manager");
        stage.setMinWidth(520);
        stage.setMinHeight(480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
