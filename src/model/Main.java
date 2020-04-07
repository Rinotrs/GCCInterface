package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/enterPage.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        primaryStage.setTitle("GCC Interface");
        primaryStage.setScene(new Scene(mainScreen, 1000, 800));
        primaryStage.setResizable(false);
//        workingPageController controller = fxmlLoader.getController();
//        controller.init();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}