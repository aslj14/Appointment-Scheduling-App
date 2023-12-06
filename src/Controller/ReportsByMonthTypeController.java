package Controller;

import javafx.fxml.FXML;
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

}
