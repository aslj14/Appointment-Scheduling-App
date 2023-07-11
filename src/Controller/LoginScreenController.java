package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    Stage stage;
    Parent scene;

        @FXML
        private TextField loginscreenpassword;

        @FXML
        private TextField loginscreenusername;

        @FXML
        void onActionDisplayMainAppts(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void onActionResetFields(ActionEvent event) {
            loginscreenusername.setText("");
            loginscreenpassword.setText("");
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
