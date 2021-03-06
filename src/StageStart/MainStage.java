package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/MainInterview.fxml"));
        primaryStage.setTitle("仓  库");
        primaryStage.setScene(new Scene(root, 640, 458));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
