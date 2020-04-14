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

/**
 *
 * This is the page for output terminal
 *
 * @author Runsen Tian
 * @version v1.0.0
 */



public class TerminalOutput implements Initializable {

    @FXML
    private TextArea outputArea;

    @FXML
    private AnchorPane backgroundPane;

    public static String currentCommand;

    /**
     *<p>
     * This method to set current command.
     *</p>
     * @param command String
     */
    public static void setCurrentCommand(String command){
        currentCommand = command;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backgroundPane.styleProperty().set("-fx-background-color: black;");
        outputArea.setStyle("-fx-text-fill:green;-fx-font-size:16px;");
        outputArea.setMinSize(300,300);

        TerminalOutputStream.setTerminalTo(outputArea);



        try {
            SOEN6751_GccProcLib.run(currentCommand);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
