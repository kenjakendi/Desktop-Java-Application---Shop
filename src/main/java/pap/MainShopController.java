package pap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainShopController implements Initializable {
    @FXML
    private ListView<String> basketView;
    @FXML
    Label test;
    private Stage stage;
    private Scene scene;
    private Parent root;
    static Map<String, Integer> bask = new HashMap<>(); // to ma być koszyk

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        basketView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String CurrListItem = basketView.getSelectionModel().getSelectedItem();
                test.setText(CurrListItem);
            }   // odczytuje nazwę wybranego produktu z koszyka
        });
    }

    public void refreshList(){  // żeby dodawać do koszyka trzeba go zmieniać na array[]
        String[] basket = bask.keySet().toArray(new String[0]);
        basketView.getItems().addAll(basket);
    }

    public void addItem(String name){   // ma być w baskecie ale nie mamy Itemów do dodawania i ciężko testować
        bask.put(name,1);
        refreshList();
    }

    public void remove(ActionEvent event){
        try {
            String[] basket = bask.keySet().toArray(new String[0]);
            int index = basketView.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            basketView.getItems().remove(index);
            bask.remove(basket[index]);
        }
        catch (Exception e){
            System.out.println("empty basket");
        }
    }

    public void switchToPay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Pay.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLogIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Articles.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
