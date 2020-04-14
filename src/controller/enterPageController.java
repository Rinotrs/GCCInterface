package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.SMARTGCC_LegacySwingVersion;

import java.net.URL;
import java.util.ResourceBundle;

public class enterPageController implements Initializable {
    @FXML
    private RadioButton expert;

    @FXML
    private Label lbl;

    @FXML
    private RadioButton typical;

    @FXML
    private ToggleGroup experience;

    @FXML
    private RadioButton novice;

    @FXML
    private Button closeButton;

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) typical.getScene().getWindow();
        stage.close();
    }

    @FXML
    void clickSure(ActionEvent event) {
        try {
            int userType = -1;
            if (novice.isSelected()) {
                //SMARTGCC_LegacySwingVersion.startSMARTGCC("novice");
                userType = 1;
            } else if (typical.isSelected()) {
                //SMARTGCC_LegacySwingVersion.startSMARTGCC("typical");
                userType = 2;
            } else {
                //SMARTGCC_LegacySwingVersion.startSMARTGCC("expert");
                userType = 3;
            }
            //TODO：要做3 个页面 workingPage_1.fxml, workingPage_2.fxml, workingPage_3.fxml
            // 1 Novice,2 Typical,3 Expert
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/workingPage_" + userType + ".fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Window for user type: " + userType);
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)novice.getScene().getWindow()).close();
            ;


        } catch (Exception e) {
            System.out.println("Cannot load working page");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
