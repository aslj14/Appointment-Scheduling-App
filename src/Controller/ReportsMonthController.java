package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReportsMonthController {

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
    private RadioButton totalmonthtyperadio;

}
