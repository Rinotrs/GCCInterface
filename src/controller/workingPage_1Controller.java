package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import util.GCCDocParser;
import util.SOEN6751_OptionsModel;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class workingPage_1Controller implements Initializable {

    ProcessBuilder processBuilder = new ProcessBuilder();

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
    private TextFlow textField;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
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
        scrollPane.setFitToWidth(true);

        grid.getChildren().removeAll();
        grid.setGridLinesVisible(false);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(200,200,230),CornerRadii.EMPTY, Insets.EMPTY)));
        /*ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33);
        grid.getColumnConstraints().addAll(col1,col2,col3);*/



    }

    private void loadData() {
        unUsedList.addAll("Code Generation", "Developer Options", "Code Optimization");
        series.getItems().addAll(unUsedList);
        allButton.add(codeGeneration);
        allButton.add(developerOptions);
        allButton.add(codeOptimization);

    }

    public void clickCompilerOptions(ActionEvent actionEvent) {
        grid.getChildren().clear();
        //3 buttons each row
        int columns = 3;

        int num_btn = SOEN6751_OptionsModel.compiler.length;
        int index = 0;
        int row = 0;
        String [] options = SOEN6751_OptionsModel.compiler;

        while(index < num_btn) {
            final String op = options[index];

            for (int i = 0; i < columns; i++) {
                Button b = new Button(SOEN6751_OptionsModel.compiler[index]);
                b.setAlignment(Pos.CENTER);
                b.setMinWidth(224);
                b.setMinHeight(40);
                b.setAccessibleText(options[index]);
                b.setTooltip(new Tooltip(GCCDocParser.getParameterDescription(SOEN6751_OptionsModel.compiler_documentation,op)));

                grid.add(b, i, row);
            }
            index++;
            if(index%3==0)row++;
        }

    }

    public void clickDebuggingOptions(ActionEvent actionEvent) {
        grid.getChildren().clear();

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
