package pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.*;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ArticlesController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean popUpOpen = false;
    @FXML
    private TextField productName;
    Warehouse warehouse = new Warehouse();
    ArrayList<String> itemsNameList = warehouse.getItemsNameList();
    @FXML
    private ListView<String> productList;

    @FXML
    public void search(ActionEvent event){
        productList.getItems().clear();
        productList.getItems().addAll(searchList(productName.getText(),itemsNameList));
    }

    public void itemDetails(MouseEvent event) throws IOException {
        String itemName = productList.getSelectionModel().getSelectedItem();
        Item item = warehouse.findItemByName(itemName);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowItem.fxml"));
        Parent root = loader.load();
        ShowItemController showItem = loader.getController();
        showItem.setAvailableAmount(item);
        showItem.setItem(item);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public Item selectedItem(){
        String itemName = productList.getSelectionModel().getSelectedItem();
        return warehouse.findItemByName(itemName);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings){
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    public void switchToMainShop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getProductName(ActionEvent event) throws IOException {
        Item item = selectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        MainShopController mainShop = loader.getController();
        try {
            mainShop.addItem(item);
        }
        catch (Exception NullPointerException)
        {
            System.out.println("no product chosen");
        };
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productList.getItems().addAll(itemsNameList);
    }
}
