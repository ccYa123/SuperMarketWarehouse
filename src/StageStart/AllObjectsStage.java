package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AllObjectsStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/AllObjectsInterview.fxml"));
        primaryStage.setTitle("所有商品");
        primaryStage.setScene(new Scene(root, 781, 428));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
