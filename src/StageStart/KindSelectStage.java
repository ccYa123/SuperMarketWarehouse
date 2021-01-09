package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KindSelectStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Interviewer/KindSelectInterview.fxml"));
        primaryStage.setTitle("按类别查找商品");
        primaryStage.setScene(new Scene(root, 700, 573));
        primaryStage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
