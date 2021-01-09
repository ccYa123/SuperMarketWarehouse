package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddNewStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/AddNewInterview.fxml"));
        primaryStage.setTitle("进   货");
        primaryStage.setScene(new Scene(root, 603, 432));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
