package Controller;

import StageStart.EditStage;
import Util.DbConnection;
import Util.DbUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

/**
 * 删除商品界面控制类
 */
public class DeleController implements Initializable {
    @FXML
    private Label Num;
    @FXML
    private Label Name;
    @FXML
    private AnchorPane deletePane;

    Connection conn= null;
    PreparedStatement stmt = null;
    String NUM = AllObjectsController.getNum();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DbUtil du = new DbUtil();
        Num.setText(du.selectObjects(NUM).getNum());
        Name.setText(du.selectObjects(NUM).getName());
    }

    /**
     * 删除按钮监听事件
     */
    public void delete(){
        DbUtil du = new DbUtil();
        du.deleteObjects(NUM);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage1 = (Stage) deletePane.getScene(). getWindow();
        stage1.close();
        alert.titleProperty().set("提示！");
        alert.headerTextProperty().set("删除成功！");
        alert.showAndWait();

    }
    public void cancel() throws Exception {
        Stage stage = (Stage) deletePane.getScene(). getWindow();
        stage.close();
        EditStage editStage = new EditStage();
        editStage.start(new Stage());
    }
}
