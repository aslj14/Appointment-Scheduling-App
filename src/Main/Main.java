package Main;

import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 500);
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //Locale.setDefault(new Locale("fr"));
        //System.out.println(ZoneId.systemDefault());
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
