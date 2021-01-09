package Controller;

import Util.DbConnection;
import Util.DbUtil;
import Util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * 新增商品控制类
 */
public class AddNewController {

    @FXML
    private TextField num;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField kind;
    @FXML
    private TextField sum;//进货量
    @FXML
    private TextField sell;
    @FXML
    private AnchorPane addPane;
    Connection conn = null;
    PreparedStatement stmt = null;

    @FXML
    public void addOperate() throws SQLException {
        String Num = num.getText();
        String Name = name.getText();
        double Price = Double.valueOf(price.getText());
        String Kind = kind.getText();
        int Sum = Integer.valueOf(sum.getText());
        String Sell = sell.getText();
        int Sale = 0;
        DbUtil dbu = new DbUtil();
        Objects obj;
        if(dbu.judgeObjects(Num)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("商品编号已存在，请重新输入！");
            alert.showAndWait();
            num.setText("");
        }else if(!dbu.judgeObjects(Num)&&!Num.equals("")&&!Name.equals("")&&Price!=0&&!Kind.equals("")&&Sum!=0&&!Sell.equals("")){
            obj = new Objects(Num, Name, Price, Kind, Sale,Sum, Sell);
            dbu.addObjects(obj);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("进货成功");
            alert.showAndWait();
            num.setText("");
            name.setText("");
            price.setText("");
            kind.setText("");
            sum.setText("");
            sell.setText("");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("不能为空！");
            alert.showAndWait();
        }
    }
    @FXML
    public void back() {
        Stage stage = (Stage) addPane.getScene().getWindow();
        stage.close();
    }
}
