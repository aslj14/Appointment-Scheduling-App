package Controller;

import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static DAO.DatabaseLogin.databaseLoginQuery;
public class LoginScreenController implements Initializable {

    Stage stage;
    Parent scene;

        @FXML
        private Button loginbutton;

        @FXML
        private TextField loginscreenpassword;

        @FXML
        private TextField loginscreenusername;

        @FXML
        private Label mainscreenlabel;

        @FXML
        private Label passwordlabel;

        @FXML
        private Button resetbutton;

        @FXML
        private Label timezonelabel;

        @FXML
        private Label usernamelabel;

        @FXML
        private Label zonelabel;

        @FXML
        void onActionDisplayMainAppts(ActionEvent event) throws IOException {

            try {
                String userName = loginscreenusername.getText();
                String userPassword = loginscreenpassword.getText();
                Users usersResults = databaseLoginQuery(userName, userPassword);
                if (usersResults != null) {
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
                    Scene scene = new Scene(loader.load());
                    stage.setTitle("Appointment Scheduling System");
                    stage.setScene(scene);
                    stage.show();
                } else if (Locale.getDefault().getLanguage().equals("fr")) {
                    ResourceBundle rb = ResourceBundle.getBundle("bundle/Languages_fr", Locale.getDefault());

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(rb.getString("Invalid") + " " + rb.getString("username") + " " +
                                         rb.getString("and") + "/" + rb.getString("or") + " " +
                                         rb.getString("password") + " " + rb.getString("combination") + ". " +
                                         rb.getString("Try") + " " + rb.getString("again") + "!");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid username and/or password combination. Try again!");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
        }


        @FXML
        void onActionResetFields(ActionEvent event) {
            this.loginscreenusername.setText("");
            this.loginscreenpassword.setText("");
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          try {
              if (Locale.getDefault().getLanguage().equals("fr")) {
                  ResourceBundle rb = ResourceBundle.getBundle("bundle/Languages_fr", Locale.getDefault());

                  mainscreenlabel.setText(rb.getString("LOGIN"));
                  usernamelabel.setText(rb.getString("Username"));
                  passwordlabel.setText(rb.getString("Password"));
                  loginbutton.setText(rb.getString("Login"));
                  resetbutton.setText(rb.getString("Reset"));
                  timezonelabel.setText(rb.getString("Timezone"));
                  stage.setTitle(rb.getString("Appointment") + " " + rb.getString("Scheduling") +
                                " " + rb.getString("System"));
              }
          } catch (Exception e) {
                  System.out.println("Error:" + e.getMessage());
           }
        }
}
