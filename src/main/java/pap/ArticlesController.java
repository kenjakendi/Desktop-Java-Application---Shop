package pap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    ArrayList<String> lista = new ArrayList<>(
            Arrays.asList("jabłko", "banany", "pomarańcza")
            );
    @FXML
    private ListView<String> productList;

    @FXML
    public void search(ActionEvent event){
        productList.getItems().clear();
        productList.getItems().addAll(searchList(productName.getText(),lista));
    }

    public String selectedItem(){
        String item = productList.getSelectionModel().getSelectedItem();
        return item;
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
        MainShopController mainShop = loader.getController();
        mainShop.refreshList();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void getProductName(ActionEvent event) throws IOException {
        String name = selectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        MainShopController mainShop = loader.getController();
        if (name != null)
            mainShop.addItem(name);
        else
            mainShop.refreshList();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productList.getItems().addAll(lista);
    }
}