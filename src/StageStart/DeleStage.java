package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeleStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/DeleInterview.fxml"));
        primaryStage.setTitle("确认删除");
        primaryStage.setScene(new Scene(root, 600, 244));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
