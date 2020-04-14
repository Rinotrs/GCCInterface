package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import util.SOEN6751_GccProcLib;
import util.SOEN6751_TerminalOutput;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class TerminalOutput implements Initializable {

    @FXML
    private TextArea outputArea;

    @FXML
    private AnchorPane backgroundPane;

    public static String currentCommand;

    public static void setCurrentCommand(String command){
        currentCommand = command;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backgroundPane.styleProperty().set("-fx-background-color: black;");

        outputArea.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY)));
        outputArea.setStyle("-fx-text-fill:green;-fx-font-size:16px;");
        outputArea.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);

        TerminalOutputStream.setTerminalTo(outputArea);
    }


}
