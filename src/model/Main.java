package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class which is the starting point of the GCC Interface.
 *
 * @author Runsen Tian
 * @version 1.0.0
 */

public class Main extends Application {

    /**
     *
     * This method loads the main screen of the GCC Interface.
     *
     * @param primaryStage the first screen user is exposed to.
     * @throws Exception all the exceptions that didnt't handle within the functions
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/enterPage.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        primaryStage.setTitle("GCC Interface");
        primaryStage.setScene(new Scene(mainScreen, 1000, 600));
        primaryStage.setResizable(false);
//        workingPageController controller = fxmlLoader.getController();
//        controller.init();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}