package pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PayController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMainShop(ActionEvent event) throws Exception {
        buy();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buy() throws Exception {
        DBinquiry db = new DBinquiry();
        double price = Basket.calculatePrice();
        db.insertStatistics(Warehouse.getLASTid()+1, price);
        Basket.buy();

    }
}
