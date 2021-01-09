package Controller;

import StageStart.LoginStage;
import StageStart.RegisterStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private AnchorPane StartPane;

    public void enter() throws Exception {
        LoginStage loginStage = new LoginStage();
        loginStage.start(new Stage());
        Stage stage= (Stage) StartPane.getScene().getWindow();
        stage.close();
    }
}
