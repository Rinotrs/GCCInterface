package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class workingPage_3Controller {

    @FXML
    Button compilerOptions;
    @FXML
    Button debuggingOptions;
    @FXML
    Button linkingOptions;
    @FXML
    Button executeOptions;
    @FXML
    Button codeGeneration;
    @FXML
    Button developerOptions;
    @FXML
    Button codeOptimization;

    @FXML
    private Button closeButton;

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public void clickCompilerOptions(ActionEvent actionEvent) {
    }

    public void clickDebuggingOptions(ActionEvent actionEvent) {
    }

    public void clickLinkingOptions(ActionEvent actionEvent) {
    }

    public void clickExecuteOptions(ActionEvent actionEvent) {
    }

    public void clickCodeGeneration(ActionEvent actionEvent) {
    }

    public void clickDeveloperOptions(ActionEvent actionEvent) {
    }

    public void clickCodeOptimization(ActionEvent actionEvent) {
    }
}
