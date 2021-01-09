package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/EditInterview.fxml"));
        primaryStage.setTitle("编辑商品信息");
        primaryStage.setScene(new Scene(root, 277, 482));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
