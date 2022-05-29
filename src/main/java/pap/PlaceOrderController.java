package pap;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PlaceOrderController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    static Map<Item, Integer> itemsToAdd = new HashMap<>();

    @FXML
    private TableView<SupplierItem> orderTable;
    @FXML
    private TableColumn<SupplierItem, String> name_col;
    @FXML
    private TableColumn<SupplierItem, Integer> quant_col;
    @FXML
    private TableColumn<SupplierItem, String> addit_col;
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemQuant;
    @FXML
    private TextField itemComm;

    static ArrayList<SupplierItem> tempOrder = new ArrayList<>();

    ObservableList<SupplierItem> items = FXCollections.observableArrayList(tempOrder);;

    public void switchToManager(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerPage.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void placeOrder(ActionEvent event){
        Warehouse warehouse = new Warehouse();
        warehouse.addMapOfItems(itemsToAdd);
        tempOrder.clear();
        items.clear();
        itemsToAdd.clear();
    }

    public void delete(ActionEvent event){
        SupplierItem selectedItem = orderTable.getSelectionModel().getSelectedItem();
        orderTable.getItems().remove(selectedItem);

        items = orderTable.getItems();
        orderTable.setItems(items);
        tempOrder = new ArrayList<>(items);

        itemsToAdd.clear();
        for (SupplierItem item: tempOrder) {
            Item itemToAdd = new Item(item.getName());
            itemsToAdd.put(itemToAdd, item.getQuantity());
        }
    }

    public void submit(ActionEvent event){
        try {
            SupplierItem supplierItem = new SupplierItem(
                    itemName.getText(),
                    Integer.parseInt(itemQuant.getText()),
                    itemComm.getText());
            items.add(supplierItem);
            Item itemToAdd;
            if (Warehouse.convertItem(supplierItem) != null){
                itemToAdd = Warehouse.convertItem(supplierItem);
            }
            else {
                itemToAdd = new Item(supplierItem.getName());}
            itemsToAdd.put(itemToAdd,  supplierItem.getQuantity());
            items = orderTable.getItems();
            orderTable.setItems(items);
            tempOrder = new ArrayList<>(items);
        }
        catch (Exception NumberFormatException)
        {
            System.out.println("id and quantity must be an integer");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        quant_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        addit_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        orderTable.setItems(items);
    }
}
