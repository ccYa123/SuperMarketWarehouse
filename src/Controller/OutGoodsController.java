package Controller;

import Util.DbUtil;
import Util.checkBox;
import Util.DbConnection;
import Util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;

/**
 * 出货界面控制类
 */
public class OutGoodsController implements Initializable {
    @FXML
    private TableColumn<Objects, CheckBox> choose;
    @FXML
    private TableView<Objects> objectsTable;
    @FXML
    private TableColumn<Objects, String> Num;
    @FXML
    private TableColumn<Objects, String> Name;
    @FXML
    private TableColumn<Objects, Double> Price;
    @FXML
    private TableColumn<Objects, String> Kind;
    @FXML
    private TableColumn<Objects, Integer> Sales;
    @FXML
    private TableColumn<Objects, Integer> Sum;
    @FXML
    private TableColumn<Objects, String> Sell;
    @FXML
    private TextField orderNum;
    @FXML
    private TextField orderTime;
    @FXML
    private TextField orderSum;
    @FXML
    private AnchorPane outGoodsPane;

    Connection conn = null;
    PreparedStatement stmt = null;

    Objects obj=null;

    private ObservableList<Objects> ObjectsList = FXCollections.observableArrayList();

    public ObservableList<Objects> getObjectsData() throws SQLException {

        String sql = "select * from warehouse.objects where sell = '上架'";
        DbConnection DbCn = new DbConnection();
        conn = DbCn.getConnection();
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ObjectsList.add(new Objects(rs.getString(1), rs.getString(2), rs.getDouble(3),
                    rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
        }
        return ObjectsList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //映射数据进每列
        Num.setCellValueFactory(new PropertyValueFactory<Objects,String>("num"));
        Name.setCellValueFactory(new PropertyValueFactory<Objects,String>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<Objects,Double>("price"));

        Kind.setCellValueFactory(new PropertyValueFactory<Objects,String>("kind"));
        Sales.setCellValueFactory(new PropertyValueFactory<Objects,Integer>("sales"));
        Sum.setCellValueFactory(new PropertyValueFactory<Objects,Integer>("sum"));
        Sell.setCellValueFactory(new PropertyValueFactory<Objects,String>("sell"));

        try {
            choose.setCellValueFactory(cellData ->cellData.getValue().cb.getCheckBox());
            objectsTable.setItems(getObjectsData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        objectsTable.setEditable(true);
    }

    /**
     * 确认出货按钮监听事件
     * @throws SQLException
     */
    public void ensure() throws SQLException {
        int flag = 0;
        ObservableList<Objects> list=objectsTable.getItems();
        String OrderNum = orderNum.getText();
        String OrderTime = orderTime.getText();
        String OrderSum = orderSum.getText();
        double money=0;
        DbConnection DbCn = new DbConnection();
        Connection conn = null;
        Statement stmt = null;
        conn=DbCn.getConnection();
        stmt = (Statement)conn.createStatement();
        ResultSet rs = stmt.executeQuery("select *from allorder");
        while(rs.next()){
            if(rs.getString(1).equals(OrderNum)){
                flag = 1;
                break;
            }
        }
        if(flag == 1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("出货编号已存在，请重新输入！");
            alert.showAndWait();
            orderNum.setText("");
            orderSum.setText("");
            orderTime.setText("");
        }else{
            for(Objects o:list)
            {
                if(o.cb.isSelected())
                {
                    String sql = "select * from objects where num ='" + o.getNum() +"'";
                    try {
                        conn = DbCn.getConnection();
                        stmt = stmt = (Statement)conn.createStatement();;
                        rs = stmt.executeQuery(sql);
                        DbUtil db = new DbUtil();
                        db.selectObjects(o.getNum());
                        while(rs.next()){
                            obj = new Objects(rs.getString(1), rs.getString(2),
                                    rs.getDouble(3), rs.getString(4),
                                    rs.getInt(5), rs.getInt(6),
                                    rs.getString(7));
                        }
                        money = money + Integer.valueOf(OrderSum)*obj.getPrice();
                        db.addOrder(OrderNum,obj.getNum(),obj.getName(),obj.getPrice(),Integer.valueOf(OrderSum));
                        int s = obj.getSum() - Integer.valueOf(OrderSum);
                        Objects objects = new Objects(obj.getNum(),obj.getName(),obj.getPrice(),obj.getKind(),obj.getSales(),s,obj.getSell());
                        db.editMessages(objects);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            DbUtil db = new DbUtil();
            db.addAllOrder(OrderNum,OrderTime,money);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage)outGoodsPane.getScene().getWindow();
            stage.close();
            alert.titleProperty().set("提示！");
            alert.headerTextProperty().set("出货成功！");
            alert.showAndWait();
        }

    }
}
