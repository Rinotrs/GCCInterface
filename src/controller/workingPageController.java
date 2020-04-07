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

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class workingPageController implements Initializable {

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

    ObservableList unUsedList = FXCollections.observableArrayList();
    ObservableList UsedList = FXCollections.observableArrayList();
    List<Button> allButton = new ArrayList<Button>();
    @FXML
    private ChoiceBox<String> series;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    private void loadData() {
        unUsedList.addAll("debuggingOptions", "compilerOptions", "linkingOptions", "executeOptions", "codeGeneration", "developerOptions", "codeOptimization");
        series.getItems().addAll(unUsedList);
        allButton.add(compilerOptions);
        allButton.add(debuggingOptions);
        allButton.add(linkingOptions);
        allButton.add(executeOptions);
        allButton.add(codeGeneration);
        allButton.add(developerOptions);
        allButton.add(codeOptimization);
        series.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                unUsedList.get(t1)
                for (Object o : unUsedList) {
                    if (unUsedList.get(t1.intValue()).equals(o)) {
                        for (Button button:allButton){
                            if (button.getId().equals(o)){
                                button.setVisible(true);
                                unUsedList.remove(o);
                                series.getItems().clear();
                                series.getItems().addAll(unUsedList);
                                break;
                            }
                        }
                    }
                }
            }
        });
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
