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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<SupplierItem> orderTable;
    @FXML
    private TableColumn<SupplierItem, Integer> id_col;
    @FXML
    private TableColumn<SupplierItem, String> name_col;
    @FXML
    private TableColumn<SupplierItem, Integer> quant_col;
    @FXML
    private TableColumn<SupplierItem, Integer> price_col;
    @FXML
    private TableColumn<SupplierItem, String> addit_col;
    @FXML
    private TextField itemId;
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemQuant;
    @FXML
    private TextField itemComm;

    public void switchToManager(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerPage.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submit(ActionEvent event){
        try {
            SupplierItem item = new SupplierItem(Integer.parseInt(itemId.getText()),
                    itemName.getText(),
                    Integer.parseInt(itemQuant.getText()),
                    itemComm.getText());
            ObservableList<SupplierItem> items = orderTable.getItems();
            items.add(item);
            orderTable.setItems(items);
        }
        catch (Exception NumberFormatException)
        {
            System.out.println("id and quantity must be an integer");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        quant_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        addit_col.setCellValueFactory(new PropertyValueFactory<>("comment"));
    }
}
