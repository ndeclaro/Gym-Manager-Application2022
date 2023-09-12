package gym_manager;

/**
 * This GymManagerMain Class run the project
 * @author Vinay Kumar, Noel Declaro
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class GymManagerMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            VBox root = (VBox)FXMLLoader.load(getClass().getResource("GymManager.fxml"));
            Scene scene = new Scene(root,640,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("GymManager");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    Stage secondStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }
}
