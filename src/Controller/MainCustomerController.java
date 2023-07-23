package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainCustomerController implements Initializable {

    @FXML
    private TableColumn<?, ?> maincustaddresscol;

    @FXML
    private TableColumn<?, ?> maincustcountrycol;

    @FXML
    private TableColumn<?, ?> maincustidcol;

    @FXML
    private TableColumn<?, ?> maincustnamecol;

    @FXML
    private TableColumn<?, ?> maincustphonenumbercol;

    @FXML
    private TableColumn<?, ?> maincustpostalcodecol;

    @FXML
    private ToggleGroup maincustsTG;

    @FXML
    private TableColumn<?, ?> maincuststatecol;

    @FXML
    private TableView<?> maincusttableview;

    @FXML
    private RadioButton maincustviewallappts;

    @FXML
    private RadioButton maincustviewallcusts;

    @FXML
    private RadioButton maincustviewbymonth;

    @FXML
    private RadioButton maincustviewbywk;

    @FXML
    void onActionAddCustomer(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMainAppts(ActionEvent event) {

    }

    @FXML
    void onActionDisplayReports(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) {

    }

    @FXML
    void onActionModifyCustomer(ActionEvent event) {

    }

    @FXML
    void onActionViewByMonth(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}