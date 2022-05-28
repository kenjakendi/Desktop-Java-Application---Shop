package pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowItemController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    Warehouse warehouse = new Warehouse();
    Basket basket = new Basket();
    @FXML
    Label amount;
    @FXML
    TextField reguiredAmount;
    @Getter @Setter
    Item item;


    public void switchToMainShop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setAvailableAmount(Item item){
        amount.setText(warehouse.getQuantity(item).toString());
    }

    public void enterAmount(ActionEvent event) throws IOException {
        int chosen = Integer.parseInt(reguiredAmount.getText());
        if (chosen <= Integer.parseInt(amount.getText())){
            for (int i = 0; i < chosen; i++)
                basket.addItem(item);
            switchToMainShop(event);
        }
        else {
            System.out.println("too many chosen");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}