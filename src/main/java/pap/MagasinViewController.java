package pap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MagasinViewController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    Warehouse warehouse = new Warehouse();
    @FXML
    private TableView<Item> orderTable;
    @FXML
    private TableColumn<Item, Integer> id_col;
    @FXML
    private TableColumn<Item, String> name_col;
    @FXML
    private TableColumn<Item, Integer> quant_col;
    @FXML
    private TableColumn<Item, Integer> price_col;
    @FXML
    private TableColumn<Item, String> addit_col;

    public ObservableList<Item> convertWarehouse(){
        ObservableList<Item> items = FXCollections.observableArrayList();
        for (Item item: warehouse.items.keySet())
        {
            items.add(item);
        }
        return items;
    }

    public void switchToManager(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerPage.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        //quant_col.setCellValueFactory(new PropertyValueFactory<>());
        addit_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        orderTable.setItems(convertWarehouse());
    }
}
