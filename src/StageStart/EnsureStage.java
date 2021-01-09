package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnsureStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/EnsureInterview.fxml"));
        primaryStage.setTitle("确 认");
        primaryStage.setScene(new Scene(root, 455, 304));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
