package Controller;

import Util.DbConnection;
import Util.DbUtil;
import Util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EnsureController implements Initializable {
    @FXML
    private Label num;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private AnchorPane ensurePane;

    Connection conn = null;
    PreparedStatement stmt = null;
    String NUM;
    String Amount;
    Objects obj;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\TXT\\Order.txt"));
            NUM = br.readLine();
            Amount = br.readLine();
            num.setText(NUM);
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            DbUtil db = new DbUtil();
            obj = db.selectObjects(NUM);
            name.setText(obj.getName());
            price.setText(String.valueOf(obj.getPrice()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ensure(){
        DbConnection DbCn = new DbConnection();
        conn = DbCn.getConnection();
        String sql = "update warehouse.Objects set sum = ? where num =?";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,obj.getSum() + Integer.valueOf(Amount));
            stmt.setString(2,obj.getNum());
            stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage1 = (Stage) ensurePane.getScene(). getWindow();
            stage1.close();
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("进货成功！");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cancel(){
        Stage stage = (Stage) ensurePane.getScene().getWindow();
        stage.close();
    }
}
