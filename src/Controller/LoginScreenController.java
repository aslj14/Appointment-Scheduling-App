package Controller;

import DAO.AppointmentsDAO;
import DAO.ImplementAppointments;
import Helper.JDBC;
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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static DAO.DatabaseLogin.databaseLoginQuery;

/**
 *
 * This is the Login Screen controller.
 *
 * <p>This controller class will supply the control and logic of the login screen. This controller class
 * will permit the user to gain access to the application. The user will be prompted to input a legitimate
 * username and password.</p>
 *
 * @author Ariel Johnson
 *
 */
public class LoginScreenController implements Initializable {

    Stage stage;
    Parent scene;

        /**
         *
         * Button for the login button.
         *
         */
        @FXML
        private Button loginbutton;

        /**
         *
         * Textfield for the login password.
         *
         */
        @FXML
        private TextField loginscreenpassword;

        /**
         *
         * Textfield for the login username.
         *
         */
        @FXML
        private TextField loginscreenusername;

        /**
         *
         * Label for the "LOGIN" prompt on the login screen.
         *
         */
        @FXML
        private Label mainscreenlabel;

        /**
         *
         * Label for "Password:" prompt on the login screen.
         *
         */
        @FXML
        private Label passwordlabel;

        /**
         *
         * Button to reset on the login screen.
         *
         */
        @FXML
        private Button resetbutton;

        /**
         *
         * Label for the timezone on the login screen.
         *
         */
        @FXML
        private Label timezonelabel;

        /**
         *
         * Label for "Username:" prompt on the login screen.
         *
         */
        @FXML
        private Label usernamelabel;

        /**
         *
         * Label for user's default timezone on the login screen.
         *
         */
        @FXML
        private Label zonelabel;

        /**
         *
         * This is the method to log on to the application.
         *
         * <p>In this method, the user inputs a username and password to access the application. The username and password
         * are both entered into their respective textfields. The "databaseLoginQuery" method is then called and takes into
         * account the username and password that the user entered.</p>
         *
         * <p>The file "login_activity.txt" is created which saves the user login information to
         * the file. Each time a user successfully or unsuccessfully logs in, the data will be saved to this file.</p>
         *
         * <p>If the user sets their operating system's language to French, the login screen also has the
         * functionality to display all of the information on the login screen in French.</p>
         *
         * @param event This is the action for the Login button on the Login Screen.
         * @throws IOException From the FXMLLoader.
         *
         */
        @FXML
            void onActionDisplayMainAppts(ActionEvent event) throws IOException {

                try {
                    String userName = loginscreenusername.getText();
                    String userPassword = loginscreenpassword.getText();
                    Users usersResults = databaseLoginQuery(userName, userPassword);
                    LocalDateTime current = LocalDateTime.now();
                    String file = "login_activity.txt";

                    FileWriter fileW = new FileWriter(file, true);
                    PrintWriter fileToOutput = new PrintWriter(fileW);

                    if (usersResults != null) {
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
                        Scene scene = new Scene(loader.load());
                        stage.setTitle("Appointment Scheduling System");
                        stage.setScene(scene);
                        stage.show();

                        JDBC.openConnection();
                        AppointmentsDAO appointmentsDAO = new ImplementAppointments();
                        LocalDateTime localDT = DAO.DatabaseLogin.getLoginLocalDateTime();
                        appointmentsDAO.nearingAppointmentsAlert(localDT);
                        fileToOutput.println(userName + "Login attempt at " + current + " (" + ZoneId.systemDefault() + ") was successful!");
                    } else if (Locale.getDefault().getLanguage().equals("fr")) {
                        ResourceBundle rb = ResourceBundle.getBundle("bundle/Languages_fr", Locale.getDefault());

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(rb.getString("Invalid") + " " + rb.getString("username") + " " +
                                             rb.getString("and") + "/" + rb.getString("or") + " " +
                                             rb.getString("password") + " " + rb.getString("combination") + ". " +
                                             rb.getString("Try") + " " + rb.getString("again") + "!");
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.showAndWait();

                        fileToOutput.println(userName + "Login attempt at " + current + " (" + ZoneId.systemDefault() + ") was unsuccessful!");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid username and/or password combination. Try again!");
                        alert.showAndWait();

                        fileToOutput.println(userName + "Login attempt at " + current + " (" + ZoneId.systemDefault() + ") was unsuccessful!");
                    }
                    fileToOutput.close();
                } catch (Exception e) {
                    System.out.println("Error:" + e.getMessage());
                }
            }

        /**
         *
         * This is the method that allows the user to reset or clear the Username and Password textfields.
         *
         * @param event This is the action for the Reset button on the Login Screen.
         */
        @FXML
            void onActionResetFields(ActionEvent event) {
                this.loginscreenusername.setText("");
                this.loginscreenpassword.setText("");
            }

        /**
         *
         * This initializes the Login Screen controller.
         *
         * <p>The user's timezone that is set on their operating system will be displyed in a label on this screen.
         * Also, if the user sets their operating system's language to French, all of the labels and text on the Login
         * Screen will be displayed in French.</p>
         *
         * @param url url The location that controls the root object's path that is relative.
         * @param resourceBundle The resources that are utilized to accommodate the root object.
         *
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                zonelabel.setText(String.valueOf(ZoneId.systemDefault()));

                  try {
                      if (Locale.getDefault().getLanguage().equals("fr")) {
                          ResourceBundle rb = ResourceBundle.getBundle("bundle/Languages_fr", Locale.getDefault());

                          mainscreenlabel.setText(rb.getString("LOGIN"));
                          usernamelabel.setText(rb.getString("Username"));
                          passwordlabel.setText(rb.getString("Password"));
                          loginbutton.setText(rb.getString("Login"));
                          resetbutton.setText(rb.getString("Reset"));
                          timezonelabel.setText(rb.getString("Timezone"));
                          //stage.setTitle(rb.getString("Appointment") + " " + rb.getString("Scheduling") +
                          //" " + rb.getString("System"));
                      }
                  } catch (Exception e) {
                          System.out.println("Error:" + e.getMessage());
                   }
                }
        }
