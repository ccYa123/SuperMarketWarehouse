package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/LoginInterview.fxml"));
        primaryStage.setTitle("登  录");
        primaryStage.setScene(new Scene(root, 407, 306));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
