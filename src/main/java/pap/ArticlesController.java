package pap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ArticlesController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField productName;


    public void switchToMainShop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainShop.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void getProductName(ActionEvent event) throws IOException {
        String name = productName.getText().toLowerCase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        MainShopController mainShop = loader.getController();
        mainShop.addItem(name);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//
//    public void fill_basket(ActionEvent event) throws IOException {
//        basketView.setCellFactory(stringListView -> {
//            try {
//                return getProductName(event);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        });
//    }
}