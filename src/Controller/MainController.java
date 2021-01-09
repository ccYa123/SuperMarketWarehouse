package Controller;

import StageStart.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 主界面控制类
 */
public class MainController {

    @FXML
    private AnchorPane mainPane;

    //查看所有商品
    public void seeAll() throws Exception {
        AllObjectsStage allObjectsStage = new AllObjectsStage();
        allObjectsStage.start(new Stage());
    }

    //按类别查找商品
    public void kindSelect() throws Exception {
        KindSelectStage kindSelectStage = new KindSelectStage();
        kindSelectStage.start(new Stage());
    }
    //下架商品
    public void unsellObjects() throws Exception {
        UnsellObjectsStage unsellObjectsStage = new UnsellObjectsStage();
        unsellObjectsStage.start(new Stage());
    }
    //上架商品
    public void sellObjects() throws Exception {
        SellObjectsStage sellObjectsStage = new SellObjectsStage();
        sellObjectsStage.start(new Stage());
    }

    public void exit() throws Exception {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        StartStage startStage = new StartStage();
        startStage.start(new Stage());
    }

    public void addNew() throws Exception {
       AddNewStage addGoodsStage = new AddNewStage();
       addGoodsStage.start(new Stage());
    }

    public void addExist() throws Exception {
        AddExitStage addExitStage = new AddExitStage();
        addExitStage.start(new Stage());
    }
    public void outGoods() throws Exception {
        OutGoodsStage outGoodsStage = new OutGoodsStage();
        outGoodsStage.start(new Stage());
    }
}
