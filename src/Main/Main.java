package Main;

import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Locale;

/**
 * This Appointment Scheduling App implements and extends an
 * application that manages appointments within a database that includes
 * a number of customers and contacts.
 *
 * @author Ariel Johnson
 *
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(loader.load(), 700, 400);
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the main method of the program. This will be the first
     * method that gets called when the program runs. The JDBC connection will be
     * opened before the program is launched. The connection will then close after
     * the program itself is closed.
     * @param args These are the String arguments.
     */
    public static void main(String[] args) {
        //Locale.setDefault(new Locale("fr"));
        //System.out.println(ZoneId.systemDefault());
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
