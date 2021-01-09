package Controller;

import StageStart.DeleStage;
import StageStart.EditStage;
import Util.DbConnection;
import Util.DbUtil;
import Util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.TextFieldTableCell.*;

public class AllObjectsController implements Initializable {

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
    private TableColumn<Objects, String> edit;
    @FXML
    private AnchorPane allObjectsPane;

    private static String NUM;

    Connection conn = null;
    PreparedStatement stmt = null;

    public static String getNum(){
        return AllObjectsController.NUM;
    }

    /**
     * 存表格里数据的集合
     */
    private ObservableList<Objects> ObjectsList = FXCollections.observableArrayList();

    /**
     * 将数据库中的商品信息存入ObjectList中
     * @return
     * @throws SQLException
     */
    public ObservableList<Objects> getObjectsData() throws SQLException {

        String sql = "select * from warehouse.objects";
        DbConnection DbCn = new DbConnection();
        conn = DbCn.getConnection();
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ObjectsList.add(new Objects(rs.getString(1), rs.getString(2), rs.getDouble(3),
                    rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
        }

        edit.setCellFactory((col) -> {
            TableCell<Objects, String> cell = new TableCell<Objects, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Button delBtn = new Button("编辑");
                        this.setGraphic(delBtn);
                        delBtn.setOnMouseClicked((me) -> {
                            Objects objects = this.getTableView().getItems().get(this.getIndex());//得到一整列对象
                            NUM = objects.getNum();
                            EditStage editStage = new EditStage();
                            try {
                                editStage.start(new Stage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            };
            return cell;
        });
        return ObjectsList;
    }


    /**
     * 将集合中的数据放入表格中并在界面显示出来
     * @param location
     * @param resources
     */
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
            objectsTable.setItems(getObjectsData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        objectsTable.setEditable(true);
    }

    public void refresh() throws SQLException {
        ObjectsList.clear();
        objectsTable.setItems(getObjectsData());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.titleProperty().set("提示！");
        alert.headerTextProperty().set("刷新成功！");
        alert.showAndWait();
    }
    public void back(){
        Stage stage =(Stage)allObjectsPane.getScene().getWindow();
        stage.close();
    }
}
