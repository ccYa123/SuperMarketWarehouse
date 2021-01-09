package Controller;

import StageStart.MainStage;
import Util.DbConnection;
import Util.DbUtil;
import Util.Objects;
import com.sun.org.omg.CORBA.Initializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 根据商品类别查询商品界面控制类
 */
public class KindSelectController implements Initializable {

    @FXML
    private TableView<Objects> objectsTable;
    @FXML
    private TableColumn<Objects,String> Num;
    @FXML
    private TableColumn<Objects,String> Name;
    @FXML
    private TableColumn<Objects,Double> Price;
    @FXML
    private TableColumn<Objects,String> Kind;
    @FXML
    private TableColumn<Objects,Integer> Sales;
    @FXML
    private TableColumn<Objects,Integer> Sum;
    @FXML
    private TableColumn<Objects,String> Sell;
    @FXML
    private TextField K;
    @FXML
    private AnchorPane kindSelectPane;

    Connection conn = null;
    PreparedStatement stmt = null;
    private ObservableList<Objects> ObjectsList = FXCollections.observableArrayList();

    public ObservableList<Objects> getObjectsData() throws SQLException {

        String sql = "select * from warehouse.objects " ;
        DbConnection DbCn = new DbConnection();
        conn = DbCn.getConnection();
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            ObjectsList.add(new Objects(rs.getString(1), rs.getString(2), rs.getDouble(3),
                    rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getString(7)));
        }
        return ObjectsList;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //映射数据进每列
        Num.setCellValueFactory(new PropertyValueFactory<>("num"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        Kind.setCellValueFactory(new PropertyValueFactory<>("kind"));
        Sales.setCellValueFactory(new PropertyValueFactory<>("sales"));
        Sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        Sell.setCellValueFactory(new PropertyValueFactory<>("sell"));

        try {
            objectsTable.setItems(getObjectsData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        objectsTable.setEditable(true);
    }
    public void find() throws SQLException {
        String k = K.getText();
        String sql = "select * from warehouse.objects where kind = '"+k+"'";
        DbConnection DbCn = new DbConnection();
        conn = DbCn.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ObjectsList.clear();
        while(rs.next()){
            ObjectsList.add(new Objects(rs.getString(1), rs.getString(2), rs.getDouble(3),
                    rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getString(7)));
        }
        objectsTable.setItems(ObjectsList);
        objectsTable.setEditable(true);
    }
    public void back() throws Exception {
        Stage stage = (Stage) kindSelectPane.getScene().getWindow();
        stage.close();
    }
}
