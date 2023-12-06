package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class ReportsCountryController {

    @FXML
    private RadioButton contactsched;

    @FXML
    private ComboBox<?> countrycombobox;

    @FXML
    private TableView<?> countrycuststableview;

    @FXML
    private TableColumn<?, ?> custaddresscol;

    @FXML
    private TableColumn<?, ?> customeridcol;

    @FXML
    private TableColumn<?, ?> custscountrycol;

    @FXML
    private TableColumn<?, ?> custsnamecol;

    @FXML
    private TableColumn<?, ?> custspostalcol;

    @FXML
    private TableColumn<?, ?> custsstateprovincecol;

    @FXML
    private ToggleGroup totalbycountryTG;

    @FXML
    private RadioButton totalbymonthtype;

    @FXML
    private RadioButton totalcustsbycountry;

    @FXML
    private Label totalcustslabel;

    @FXML
    void onActionDisplayContactSchedule(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMain(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMonthType(ActionEvent event) {

    }

    @FXML
    void onActionExit(ActionEvent event) {

    }

    @FXML
    void onActionFillTableview(ActionEvent event) {

    }

}
