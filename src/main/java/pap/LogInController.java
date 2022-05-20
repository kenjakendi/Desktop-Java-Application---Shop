package pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class LogInController {

    DBinquiry logininquiry = new DBinquiry();

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public TextField login;
    @FXML
    private TextField password;
    @FXML
    private Label loginStatus;
    static public boolean logged;

    public void switchToMainShop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        MainShopController mainShop = loader.getController();
        mainShop.refreshList();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void setLogged(boolean status){
        logged = status;
    }

   /*
   // do wywalenia ???
   public void switchToManagerPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManagerPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    */

    public void switchToManagerPage(String log, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerPage.fxml"));
        root = loader.load();
        ManagerPageController managerPage = loader.getController();
        managerPage.changeName(log);
        setLogged(true);
        //MainShopController.showLogOut();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public boolean getLogged(){
        return logged;
    }

    public Map tryLogging(ActionEvent event) throws IOException {
        String log = login.getText();
        String pass = password.getText();
        Manager our_manager = new Manager(log, pass);
        System.out.println(our_manager.userData.keySet() + " " + our_manager.userData.values());

        try{
            if(this.logininquiry.isLogin(login.getText(), password.getText())){
                switchToManagerPage(log, event);
            }
            else
                this.loginStatus.setText("Złe dane");
        } catch (Exception localException){
            System.out.println("eerrr");
        }




        //w tym miejscu możemy sprawdzać, czy obiekt managera istnieje w naszej bazie dannych z pracownikami. jeśli tak, to przechodzimy do strony ManagerPage

        /*
        // raczej do wywalenia bo w innej metodzie już
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerPage.fxml"));
        root = loader.load();
        ManagerPageController managerPage = loader.getController();
        managerPage.changeName(log);
        logged = true;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/


        //switchToManagerPage(event);
        return our_manager.userData;
    }

    public void Tescik(MouseEvent event) throws IOException {
        System.out.println("It works");
    }
}
