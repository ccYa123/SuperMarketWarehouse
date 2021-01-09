package Controller;

import Util.DbConnection;
import Util.DbUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private AnchorPane RegisterPane;

    public void register() throws SQLException {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

        String usernameText = username.getText();
        String password1Text = password1.getText();
        String password2Text = password2.getText();

        int flag =0;
        String regex7 = "[a-zA-Z0-9]{6,10}";
        boolean P1 =password1Text.matches(regex7);

        conn = new DbConnection().getConnection();
        stmt = (Statement)conn.createStatement();
        rs = stmt.executeQuery("select * from warehouse.user");
        while(rs.next()){
            if(rs.getString(1).equals(usernameText)){
                flag = 1;
                break;
            }
        }
        if(usernameText.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("用户名不能为空！");
            alert.showAndWait();
        }else if(flag==1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("用户名已存在,请重新输入");
            alert.showAndWait();
        }else if(P1==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("密码只能由6至10位数字或字母组成");
            alert.showAndWait();
        }else if(!password2Text.equals(password1Text)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("请检查两次输入的密码是否一致");
            alert.showAndWait();
        } else{
            DbUtil add = new DbUtil();
            add.addUser(usernameText + "",password1Text + "");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) RegisterPane.getScene().getWindow();
            stage.close();
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("注册成功！");
            alert.showAndWait();
        }
    }
}
