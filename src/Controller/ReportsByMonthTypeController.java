package Controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ReportsByMonthTypeController {

    @FXML
    private ComboBox<?> contactmonthtypebox;

    @FXML
    private RadioButton monthtypescheduleradio;

    @FXML
    private TableView<?> monthtypetableview;

    @FXML
    private TableColumn<?, ?> monthtypetotalcol;

    @FXML
    private TableColumn<?, ?> reportmonthcol;

    @FXML
    private TableColumn<?, ?> reporttypecol;

    @FXML
    private Label selectcontactlabel;

    @FXML
    private Label totalapptslabel;

    @FXML
    private RadioButton totalbycountryradio;

    @FXML
    private ToggleGroup totalbymonthtypeTG;

    @FXML
    private RadioButton totalmonthtyperadio;

    @FXML
    void onActionExit(ActionEvent event) {

    }

    @FXML
    void onActionGetContact(ActionEvent event) {

    }

    @FXML
    void onActionReturnToMainScreen(ActionEvent event) {

    }

    @FXML
    void onActionShowContactSchedule(ActionEvent event) {

    }

    @FXML
    void onActionShowMonthType(ActionEvent event) {

    }

    @FXML
    void onActionShowTotalByCountry(ActionEvent event) {

    }


}
