package pl.controllers;


import dal.AccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import pl.models.AccountModel;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane anpLogin;
    private AccountModel accountModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountModel = new AccountModel();
        showElements(anpLogin);
        anpLogin.setStyle("-fx-background-color: #474747");
    }

    private void showElements(AnchorPane anpLogin) {
        anpLogin.setPrefWidth(420);
        anpLogin.setPrefHeight(320);

        Label lblUserName = new Label();
        lblUserName.setText("Username");
        lblUserName.setLayoutX(anpLogin.getLayoutX() + 165);
        lblUserName.setLayoutY(anpLogin.getLayoutY() + 40);
        lblUserName.getStyleClass().add("label-login");

        TextField txfUserName = new TextField();
        txfUserName.setPrefWidth(260);
        txfUserName.setLayoutX(anpLogin.getLayoutX() + 80);
        txfUserName.setLayoutY(lblUserName.getLayoutY() +40);
        txfUserName.getStyleClass().add("text-field");

        Label lblPassword = new Label();
        lblPassword.setText("Password");
        lblPassword.setLayoutX(anpLogin.getLayoutX() + 165);
        lblPassword.setLayoutY(txfUserName.getLayoutY() + 55);
        lblPassword.getStyleClass().add("label-login");

        TextField txfPassword = new TextField();
        txfPassword.setPrefWidth(260);
        txfPassword.setLayoutX(anpLogin.getLayoutX() + 80);
        txfPassword.setLayoutY(lblPassword.getLayoutY() + 40);
        txfPassword.getStyleClass().add("text-field");

        Button btnLogin = new Button();
        btnLogin.setText("Login");
        btnLogin.setPrefWidth(260);
        btnLogin.setLayoutX(anpLogin.getLayoutX() + 80);
        btnLogin.setLayoutY(txfPassword.getLayoutY() + 60);
        btnLogin.getStyleClass().addAll("positive-buttons","label","app-buttons:pressed");

        anpLogin.getChildren().addAll(lblUserName,lblPassword,txfUserName,txfPassword,btnLogin);

        btnLogin.setOnAction(event ->  {
            accountModel.logInUser(lblUserName.getText(),lblPassword.getText());
            // TODO check if (retrievedPassword.equals(uPassword))
            //TODO display Main controller,oprional we can add a label in main controller for display"wellcome + name!
        });
    }


}
