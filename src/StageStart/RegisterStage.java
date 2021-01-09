package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/RegisterInterview.fxml"));
        primaryStage.setTitle("注  册");
        primaryStage.setScene(new Scene(root, 390, 272));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
