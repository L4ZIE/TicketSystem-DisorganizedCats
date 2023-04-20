package pl.controllers;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.models.AccountModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane anpLogin;
    private AccountModel accountModel;
    private static String uname;

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
        txfUserName.setLayoutY(lblUserName.getLayoutY() + 40);
        txfUserName.getStyleClass().add("text-field");

        Label lblPassword = new Label();
        lblPassword.setText("Password");
        lblPassword.setLayoutX(anpLogin.getLayoutX() + 165);
        lblPassword.setLayoutY(txfUserName.getLayoutY() + 55);
        lblPassword.getStyleClass().add("label-login");

        PasswordField psfPassword = new PasswordField();

        psfPassword.setPrefWidth(260);
        psfPassword.setLayoutX(anpLogin.getLayoutX() + 80);
        psfPassword.setLayoutY(lblPassword.getLayoutY() + 40);
        psfPassword.getStyleClass().add("text-field");

        Button btnLogin = new Button();
        btnLogin.setText("Login");
        btnLogin.setPrefWidth(260);
        btnLogin.setLayoutX(anpLogin.getLayoutX() + 80);
        btnLogin.setLayoutY(psfPassword.getLayoutY() + 60);
        btnLogin.getStyleClass().addAll("app-buttons", "positive-buttons");
        btnLogin.setStyle("-fx-font-weight: bold");

        anpLogin.getChildren().addAll(lblUserName, lblPassword, txfUserName, psfPassword, btnLogin);

        btnLogin.setOnAction(event -> {
            login(txfUserName.getText(), psfPassword.getText());
        });
        psfPassword.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                login(txfUserName.getText(), psfPassword.getText());
            }
        });
    }

    private void login(String username, String password) {
        //I'd use a separate thread but JavaFX's fxml is not thread safe
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                if (!accountModel.logInUser(username, password)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided information's are incorrect!");
                    alert.show();
                } else {
                    try {
                        uname = username;
                        URL asd = new File("src/pl/fxml/mainWindow.fxml").toURI().toURL();
                        Parent root = FXMLLoader.load(asd);
                        Scene scene = new Scene(root);
                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Welcome " + username);
                        primaryStage.setScene(scene);
                        primaryStage.initModality(Modality.APPLICATION_MODAL);
                        primaryStage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
    public static String getUsername(){
        return uname;
    }
}
