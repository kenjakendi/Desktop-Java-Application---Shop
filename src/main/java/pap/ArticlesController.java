package pap;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.util.Callback;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ArticlesController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField productName;
    Warehouse warehouse = new Warehouse();

    @FXML
    private TableView<Map.Entry<Item, Integer>> productTable;
    @FXML
    private TableColumn<Map.Entry<Item, Integer>, String> name_col;
    @FXML
    private TableColumn<Map.Entry<Item, Integer>, Double> price_col;
    @FXML
    private TableColumn<Map.Entry<Item, Integer>, Integer> quan_col;

    public ObservableList<Map.Entry<Item, Integer>> convertWarehouse(){
        ObservableList<Map.Entry<Item, Integer>> entries = FXCollections.observableArrayList(warehouse.getItems().entrySet());
        for (Iterator<Map.Entry<Item, Integer>> it = entries.iterator(); it.hasNext(); ) {
            Map.Entry<Item, Integer> entry = it.next();
            if (entry.getKey().getPrice() == 0.0 || entry.getValue() == 0 || entry.getKey().getName() == null){
                it.remove();
            }
        }
        return entries;
    }

    public Item selectedItem(){
        try {
            Item item = productTable.getSelectionModel().getSelectedItem().getKey();
            return item;
        }
        catch (Exception exception){
            System.out.println("no chosen");
            return null;
        }
    }

    public void itemDetails(MouseEvent event) throws IOException {
        Item item = selectedItem();
        if (item == null)
            return;
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowItem.fxml"));
        Parent root = loader.load();
        ShowItemController showItem = loader.getController();
        showItem.setLabels(item);
        showItem.setItem(item);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void switchToMainShop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, String> param) {
                String name = param.getValue().getKey().getName();
                ObservableValue<String> obs_name = new ReadOnlyObjectWrapper<>(name);
                return obs_name;
            }
        });

        quan_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, Integer> param) {
                int quantity = param.getValue().getValue();
                ObservableValue<Integer> obs_quantity = new ReadOnlyObjectWrapper<>(quantity);
                return obs_quantity;
            }
        });

        price_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, Double> param) {
                Double price = param.getValue().getKey().getPrice();
                ObservableValue<Double> obs_price = new ReadOnlyObjectWrapper<>(price);
                return obs_price;
            }
        });

        FilteredList<Map.Entry<Item, Integer>> filteredList = new FilteredList<>(convertWarehouse(), flag -> true);
        productName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(entry ->{
                if (newValue == null || newValue.isEmpty())
                    return true;
                String filteredPhrase = newValue.toLowerCase();
                if (entry.getKey().getName().toLowerCase().indexOf(filteredPhrase) != -1){
                    return true;
                }
                else if (String.valueOf(entry.getKey().getPrice()).indexOf(filteredPhrase) != -1){
                    return true;
                }
                else if (String.valueOf(entry.getValue()).indexOf(filteredPhrase) != -1){
                    return true;
                }
                else return false;
            });
        });

        SortedList<Map.Entry<Item, Integer>> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(productTable.comparatorProperty());
        productTable.setItems(sortedList);
    }
}
