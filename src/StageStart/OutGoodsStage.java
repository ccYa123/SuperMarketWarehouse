package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OutGoodsStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/OutGoodsInterview.fxml"));
        primaryStage.setTitle("出   货");
        primaryStage.setScene(new Scene(root, 637, 491));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
