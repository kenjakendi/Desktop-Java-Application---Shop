package pap;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeOfferController implements Initializable {
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
    private TableColumn<Warehouse, Integer> quant_col;
    @FXML
    private TableColumn<Item, Double> price_col;
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

    public void editId(TableColumn.CellEditEvent<Item, Integer> itemIntegerCellEditEvent) {
        Item item = orderTable.getSelectionModel().getSelectedItem();
        item.setId(itemIntegerCellEditEvent.getNewValue());
    }

    public void editName(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        Item item = orderTable.getSelectionModel().getSelectedItem();
        item.setName(itemStringCellEditEvent.getNewValue());
    }

    public void editPrice(TableColumn.CellEditEvent<Item, Double> itemDoubleCellEditEvent) {
        Item item = orderTable.getSelectionModel().getSelectedItem();
        item.setPrice(itemDoubleCellEditEvent.getNewValue());
    }

    public void editDescription(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        Item item = orderTable.getSelectionModel().getSelectedItem();
        item.setDescription(itemStringCellEditEvent.getNewValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Item, Integer> param) {
                int id = param.getValue().getId();
                ObservableValue<Integer> obs_id = new ReadOnlyObjectWrapper<>(id);
                return obs_id;
            }
        });
        name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                String name = param.getValue().getName();
                ObservableValue<String> obs_name = new ReadOnlyObjectWrapper<>(name);
                return obs_name;
            }
        });
        price_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Item, Double> param) {
                Double price = param.getValue().getPrice();
                ObservableValue<Double> obs_price = new ReadOnlyObjectWrapper<>(price);
                return obs_price;
            }
        });
//        quant_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warehouse, Integer>, ObservableValue<Integer>>() {
//            @Override
//            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Warehouse, Integer> quantity) {
//                return null;
//            }
//        });
        addit_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                String description = param.getValue().getDescription();
                ObservableValue<String> obs_description = new ReadOnlyObjectWrapper<>(description);
                return obs_description;
            }
        });
        orderTable.setItems(convertWarehouse());
        id_col.setCellFactory(TextFieldTableCell.<Item, Integer>forTableColumn(new IntegerStringConverter()));
        name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        price_col.setCellFactory(TextFieldTableCell.<Item, Double>forTableColumn(new DoubleStringConverter()));
        addit_col.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
