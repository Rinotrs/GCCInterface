package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
import java.util.*;

public class workingPage_1Controller implements Initializable {


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
    ObservableList<String> unUsedList = FXCollections.observableArrayList();
    ObservableList UsedList = FXCollections.observableArrayList();
    List<Button> allButton = new ArrayList<Button>();
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
    private TextArea textArea;


    @FXML
    private Button executeButton;


    private String currText;

    private boolean executed = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
            series.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
                int index = t1.intValue();
                String strButton = unUsedList.get(index);
                Iterator<String> iterator = unUsedList.iterator();
                while(iterator.hasNext()){
                    String s = iterator.next();
                    if (s.equals(strButton)) {
                        for (Button button : allButton) {
                            if (button.getText().equals(s)) {
                                button.setVisible(true);
                                iterator.remove();
                                break;
                            }
                        }
                    }
                    break;
                }
                series.getItems().clear();
                series.getItems().addAll(unUsedList);
            });


        scrollPane.setFitToWidth(true);

        grid.getChildren().clear();
        grid.setGridLinesVisible(false);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);

        /*ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33);
        grid.getColumnConstraints().addAll(col1,col2,col3);*/
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(100,200,200),CornerRadii.EMPTY, Insets.EMPTY)));

        currText = "gcc";
        textArea.setText(currText);
    }

    private void loadData() {
        unUsedList.addAll("Code Generation", "Code Optimization", "Developer Options");
        series.getItems().addAll(unUsedList);
//        allButton.add(compilerOptions);
//        allButton.add(debuggingOptions);
//        allButton.add(linkingOptions);
//        allButton.add(executeOptions);
        allButton.add(codeGeneration);
        allButton.add(codeOptimization);
        allButton.add(developerOptions);
    }

    public void clickCompilerOptions(ActionEvent actionEvent) {
        grid.getChildren().clear();
        //3 buttons each row
        int columns = 3;

        int num_btn = SOEN6751_OptionsModel.compiler.length;
        int index = 0;
        int row = 0;
        String[] options = SOEN6751_OptionsModel.compiler;

        while (index < num_btn) {
            final String op = options[index];

            for (int i = 0; i < columns; i++) {
                if(index == num_btn) break;
                Button b = new Button(SOEN6751_OptionsModel.compiler[index]);
                b.setAlignment(Pos.CENTER);
                b.setMinWidth(224);
                b.setMinHeight(40);
                b.setAccessibleText(options[index]);

                b.setTooltip(new Tooltip(breaklines(GCCDocParser.getParameterDescription(SOEN6751_OptionsModel.compiler_documentation,op))));
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        currText+=" "+ b.getAccessibleText();
                        textArea.setText(currText);
                    }
                });
                grid.add(b, i, row);
                index++;

            }
            index++;
            if (index % 3 == 0) row++;
        }

    }



    public void clickDebuggingOptions(ActionEvent actionEvent) {
        grid.getChildren().clear();
        //3 buttons each row
        int columns = 3;

        int num_btn = SOEN6751_OptionsModel.debugging.length;
        int index = 0;
        int row = 0;
        String [] options = SOEN6751_OptionsModel.debugging;

        while(index < num_btn) {
            final String op = options[index];

            for (int i = 0; i < columns; i++) {
                if(index == num_btn) break;
                Button b = new Button(SOEN6751_OptionsModel.debugging[index]);
                b.setAlignment(Pos.CENTER);
                b.setMinWidth(224);
                b.setMinHeight(40);
                b.setAccessibleText(options[index]);
                b.setTooltip(new Tooltip(breaklines(GCCDocParser.getParameterDescription(SOEN6751_OptionsModel.debugging_documentation,op))));
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        currText+=" "+ b.getAccessibleText();
                        textArea.setText(currText);
                    }
                });
                grid.add(b, i, row);
                index++;
            }

            if(index%3==0)row++;
        }
    }

    public void clickLinkingOptions(ActionEvent actionEvent) {
        grid.getChildren().clear();
        //3 buttons each row
        int columns = 3;

        int num_btn = SOEN6751_OptionsModel.linking.length;
        int index = 0;
        int row = 0;
        String [] options = SOEN6751_OptionsModel.linking;

        while(index < num_btn) {
            final String op = options[index];

            for (int i = 0; i < columns; i++) {
                if(index == num_btn) break;
                Button b = new Button(SOEN6751_OptionsModel.linking[index]);
                b.setAlignment(Pos.CENTER);
                b.setMinWidth(224);
                b.setMinHeight(40);
                b.setAccessibleText(options[index]);
                b.setTooltip(new Tooltip(breaklines(GCCDocParser.getParameterDescription(SOEN6751_OptionsModel.linking_documentation,op))));
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        currText+=" "+ b.getAccessibleText();
                        textArea.setText(currText);
                    }
                });
                grid.add(b, i, row);
                index++;
            }

            if(index%3==0)row++;
        }
    }

    public void clickExecuteOptions(ActionEvent actionEvent) {
        grid.getChildren().clear();
        //3 buttons each row
        int columns = 3;

        int num_btn = SOEN6751_OptionsModel.execute.length;
        int index = 0;
        int row = 0;
        String [] options = SOEN6751_OptionsModel.execute;

        while(index < num_btn) {
            final String op = options[index];

            for (int i = 0; i < columns; i++) {
                if(index == num_btn) break;
                Button b = new Button(SOEN6751_OptionsModel.execute[index]);
                b.setAlignment(Pos.CENTER);
                b.setMinWidth(224);
                b.setMinHeight(40);
                b.setAccessibleText(options[index]);
                b.setTooltip(new Tooltip(breaklines(GCCDocParser.getParameterDescription(SOEN6751_OptionsModel.execute_documentation,op))));
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        currText+=" "+ b.getAccessibleText();
                        textArea.setText(currText);
                    }
                });
                grid.add(b, i, row);
                index++;
            }
            if(index%3==0)row++;
        }
    }

    public void clickCodeGeneration(ActionEvent actionEvent) {
        grid.getChildren().clear();
        //3 buttons each row
        int columns = 3;

        int num_btn = SOEN6751_OptionsModel.generation.length;
        int index = 0;
        int row = 0;
        String [] options = SOEN6751_OptionsModel.generation;

        while(index < num_btn) {
            final String op = options[index];

            for (int i = 0; i < columns; i++) {
                if(index== num_btn) break;
                Button b = new Button(SOEN6751_OptionsModel.generation[index]);
                b.setAlignment(Pos.CENTER);
                b.setMinWidth(224);
                b.setMinHeight(40);
                b.setAccessibleText(options[index]);
                b.setTooltip(new Tooltip(breaklines(GCCDocParser.getParameterDescription(SOEN6751_OptionsModel.generation_documentation,op))));
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        currText+=" "+ b.getAccessibleText();
                        textArea.setText(currText);
                    }
                });
                index++;
                grid.add(b, i, row);
            }
            if(index%3==0)row++;
        }
    }

    public void clickDeveloperOptions(ActionEvent actionEvent) {
        grid.getChildren().clear();
        //3 buttons each row
        int columns = 3;

        int num_btn = SOEN6751_OptionsModel.developer.length;
        int index = 0;
        int row = 0;
        String [] options = SOEN6751_OptionsModel.developer;

        while(index < num_btn) {
            final String op = options[index];

            for (int i = 0; i < columns; i++) {
                if(index == num_btn) break;
                Button b = new Button(SOEN6751_OptionsModel.developer[index]);
                b.setAlignment(Pos.CENTER);
                b.setMinWidth(224);
                b.setMinHeight(40);
                b.setAccessibleText(options[index]);
                b.setTooltip(new Tooltip(breaklines(GCCDocParser.getParameterDescription(SOEN6751_OptionsModel.developer_documentation,op))));
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        currText+=" "+ b.getAccessibleText();
                        textArea.setText(currText);
                    }
                });
                grid.add(b, i, row);
                index++;
            }

            if(index%3==0)row++;
        }
    }

    public void clickCodeOptimization(ActionEvent actionEvent) {
        grid.getChildren().clear();
        //3 buttons each row
        int columns = 3;

        int num_btn = SOEN6751_OptionsModel.optimization.length;
        int index = 0;
        int row = 0;
        String [] options = SOEN6751_OptionsModel.optimization;

        while(index < num_btn) {
            final String op = options[index];

            for (int i = 0; i < columns; i++) {
                if(index == num_btn) break;
                Button b = new Button(SOEN6751_OptionsModel.optimization[index]);
                b.setAlignment(Pos.CENTER);
                b.setMinWidth(224);
                b.setMinHeight(40);
                b.setAccessibleText(options[index]);
                b.setTooltip(new Tooltip(breaklines(GCCDocParser.getParameterDescription(SOEN6751_OptionsModel.optimization_documentation,op))));
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        currText+=" "+ b.getAccessibleText();
                        textArea.setText(currText);
                    }
                });
                grid.add(b, i, row);
                index++;
            }

            if(index%3==0)row++;
        }
    }

    /**
     * Break toolTip string into multiple lines (each with 10 words max)
     * @param toolTip
     * @return
     */
    public String breaklines (String toolTip){
        String [] stringarray = toolTip.split("\\s+");
        String finished="";
        int i=0;
        while (i<stringarray.length){
            finished+= stringarray[i]+((i%10==0 && i!=0)?"\n":" ");
            i++;
        }
        return finished;
    }

    public void initializeTextArea(){
        currText="gcc";
        executed=false;
    }

    @FXML
    void executeCommand(ActionEvent event) {

        executed = true;
        initializeTextArea();
    }
}
