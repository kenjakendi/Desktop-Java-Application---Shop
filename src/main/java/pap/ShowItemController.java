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
    TextField requiredAmount;
    @Getter @Setter
    Item item;


    public void cancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setAvailableAmount(Item item){
        amount.setText(warehouse.getQuantity(item).toString());
    }

    public void enterAmount(ActionEvent event) throws IOException {
        int chosen = Integer.parseInt(requiredAmount.getText());
        int in_basket = 0;
        if (basket.containItem(item)){
            in_basket = basket.getQuantity(item);
        }
        if ((chosen <= Integer.parseInt(amount.getText())) && (chosen <= (Integer.parseInt(amount.getText())-in_basket))){
            for (int i = 0; i < chosen; i++)
                basket.addItem(item);
            cancel(event);
        }
        else {
            System.out.println("too many chosen");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}