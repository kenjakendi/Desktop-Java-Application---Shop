package pap;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MagasinViewController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    Warehouse warehouse = new Warehouse();

    ObservableList<Map.Entry<Item, Integer>> entries = FXCollections.observableArrayList(warehouse.items.entrySet());

    @FXML
    private TableView<Map.Entry<Item, Integer>> orderTable;
    @FXML
    private TableColumn<Map.Entry<Item, Integer>, Integer> id_col;
    @FXML
    private TableColumn<Map.Entry<Item, Integer>, String> name_col;
    @FXML
    private TableColumn<Map.Entry<Item, Integer>, Integer> quant_col;
    @FXML
    private TableColumn<Map.Entry<Item, Integer>, Double> price_col;
    @FXML
    private TableColumn<Map.Entry<Item, Integer>, String> addit_col;

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

        id_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, Integer> param) {
                int id = param.getValue().getKey().getId();
                ObservableValue<Integer> obs_id = new ReadOnlyObjectWrapper<>(id);
                return obs_id;
            }
        });
        name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, String> param) {
                String name = param.getValue().getKey().getName();
                ObservableValue<String> obs_name = new ReadOnlyObjectWrapper<>(name);
                return obs_name;
            }
        });
        quant_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, Integer>, ObservableValue<Integer>>() {
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
        addit_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Item, Integer>, String> param) {
                String description = param.getValue().getKey().getDescription();
                ObservableValue<String> obs_description = new ReadOnlyObjectWrapper<>(description);
                return obs_description;
            }
        });
        orderTable.setItems(entries);
    };
}
