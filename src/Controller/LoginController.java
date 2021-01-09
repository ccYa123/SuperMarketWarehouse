package Controller;

import StageStart.MainStage;
import StageStart.RegisterStage;
import Util.DbUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.Statement;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane loginPane;

    public void register() throws Exception {
        RegisterStage registerStage = new RegisterStage();
        registerStage.start(new Stage());
    }

    public void login() throws Exception {
        String usernameText = username.getText();
        String passwordText = password.getText();

        DbUtil select = new DbUtil();
        String flag = select.selectUser(usernameText);
        if(!passwordText.equals(flag)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("用户名或密码错误！");
            alert.showAndWait();
        }else{
            MainStage mainStage = new MainStage();
            mainStage.start(new Stage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.close();
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("欢迎进入仓库管理系统~");
            alert.showAndWait();
        }
    }
}
