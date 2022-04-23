package pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public TextField login;
    @FXML
    private TextField password;
    @FXML
    private TextField productName;


    public void switchToMainShop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainShop.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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

    public void switchToManagerPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManagerPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToHandlingOfGoods(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HandlingOfGoods.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToFinanceMonitor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FinanceMonitor.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public Map tryLogging(ActionEvent event) throws IOException {
        String log = login.getText();
        String pass = password.getText();
        Manager our_manager = new Manager(log, pass);
        System.out.println(our_manager.userData.keySet() + " " + our_manager.userData.values());

        //w tym miejscu możemy sprawdzać, czy obiekt managera istnieje w naszej bazie dannych z pracownikami. jeśli tak, to przechodzimy do strony ManagerPage

        switchToManagerPage(event);
        return our_manager.userData;
    }
    public String getArticleName(MouseEvent event) throws IOException {
        String name = productName.getText().toLowerCase();
        Item product = new Item(name);

        //w tym miejscu możemy sprawdzać, czy item istnieje w naszej bazie dannych z produktami. jeśli tak, to dodajemy do koszyka

        System.out.println(product.name);
        return name;
    }

    public void Tescik(MouseEvent event) throws IOException {
        System.out.println("It works");
    }
}
