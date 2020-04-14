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

public class workingPage_2Controller implements Initializable {

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
    private ChoiceBox<String> series;

    @FXML
    private Button closeButton;

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) series.getScene().getWindow();
        stage.close();
    }

    ObservableList<String> unUsedList = FXCollections.observableArrayList();
    ObservableList UsedList = FXCollections.observableArrayList();
    List<Button> allButton = new ArrayList<Button>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        series.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
//                unUsedList.get(t1)
            for (String s : unUsedList) {
                if (unUsedList.get(t1.intValue()).equals(s)) {
                    for (Button button : allButton) {
                        if (button.getId().equals(s)) {
                            button.setVisible(true);
                            unUsedList.remove(s);
                            series.getItems().clear();
                            series.getItems().addAll(unUsedList);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void loadData() {
        unUsedList.addAll("Code Optimization");
        series.getItems().addAll(unUsedList);
        allButton.add(codeGeneration);
        allButton.add(developerOptions);
        allButton.add(codeOptimization);

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
