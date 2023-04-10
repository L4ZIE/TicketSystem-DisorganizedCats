package pl.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane anpLogin;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        lblPassword.setLayoutY(txfUserName.getLayoutY() + 60);
        lblPassword.getStyleClass().add("label-login");




        anpLogin.getChildren().addAll(lblUserName,lblPassword,txfUserName);




    }
}
