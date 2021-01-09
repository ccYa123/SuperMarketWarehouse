package Controller;

import StageStart.EnsureStage;
import Util.DbConnection;
import Util.DbUtil;
import Util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 增加已存在商品库存界面控制类
 */
public class AddExitController {
    @FXML
    private TextField Num;
    @FXML
    private TextField Amount;

    Connection conn = null;
    PreparedStatement stmt = null;
    Objects obj;

    public void makeSure() throws Exception {
        String NUM = Num.getText();
        String AMOUNT = Amount.getText();
        File file = new File("src\\TXT\\Order.txt");
        DbUtil db = new DbUtil();
        obj = db.selectObjects(NUM);
        if(!obj.getNum().equals("")){
            FileWriter fw = new FileWriter("src\\TXT\\Order.txt");
            fw.write("");
            fw.write(NUM+"\n");
            fw.write(AMOUNT);
            fw.flush();
            EnsureStage ensureStage = new EnsureStage();
            ensureStage.start(new Stage());
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("商品不存在，请重新输入！");
            alert.showAndWait();
        }

    }

}
