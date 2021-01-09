package Controller;

import StageStart.DeleStage;
import Util.DbConnection;
import Util.DbUtil;
import Util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

/**
 * 编辑商品信息界面控制类
 */
public class EditController implements Initializable {

    @FXML
    private Label Num;
    @FXML
    private TextField Name;
    @FXML
    private TextField Price;
    @FXML
    private TextField Kind;
    @FXML
    private TextField Sales;
    @FXML
    private TextField Sum;
    @FXML
    private TextField Sell;
    @FXML
    public AnchorPane editPane;

    Connection conn= null;
    PreparedStatement stmt = null;
    String NUM = AllObjectsController.getNum();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DbUtil du = new DbUtil();
        Num.setText(du.selectObjects(NUM).getNum());
        Name.setText(du.selectObjects(NUM).getName());
        Price.setText(String.valueOf(du.selectObjects(NUM).getPrice()));
        Kind.setText(du.selectObjects(NUM).getKind());
        Sales.setText(String.valueOf(du.selectObjects(NUM).getSales()));
        Sum.setText(String.valueOf(du.selectObjects(NUM).getSum()));
        Sell.setText(du.selectObjects(NUM).getSell());
    }
    public void delete() throws Exception {
        DeleStage deleStage = new DeleStage();
        deleStage.start(new Stage());
        DeleController flag = new DeleController();
        Stage stage = (Stage) editPane.getScene().getWindow();
        stage.close();
    }

    /**
     * 将对商品信息的修改保存至数据库
     */
    public void save(){
        String num = Num.getText();
        String name = Name.getText();
        double price = Double.valueOf(Price.getText());
        String kind = Kind.getText();
        int sales = Integer.valueOf(Sales.getText());
        int sum = Integer.valueOf(Sum.getText());
        String sell = Sell.getText();
        if(!num.equals("")&&!name.equals("")&&!kind.equals("")&&!sell.equals("")) {
            Objects obj = new Objects(num, name, price, kind, sales, sum, sell);
            DbUtil dbUtil = new DbUtil();
            dbUtil.editMessages(obj);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage)editPane.getScene().getWindow();
            stage.close();
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("保存成功！");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("商品信息不能为空！");
            alert.showAndWait();
        }
    }
}
