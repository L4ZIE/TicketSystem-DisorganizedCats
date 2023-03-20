package pl.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    public AnchorPane anpController,
            anpContent,
            anpMain;

    public mainController() {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUserControls(anpController);
        displayEventsTableView(anpContent);
        anpMain.setStyle("-fx-background-color: #474747");
    }

    private void displayUserControls(AnchorPane container) {
        //TODO
        container.getStyleClass().add("container");

        Button manageUsers = new Button();
        Button manageEvents = new Button();
        Button manageSpecTickets = new Button();
        Label userControls = new Label();

        VBox buttonContainer = new VBox(5);

        manageUsers.getStyleClass().add("app-buttons");
        manageEvents.getStyleClass().add("app-buttons");
        manageSpecTickets.getStyleClass().add("app-buttons");

        manageUsers.setText("Manage Users");
        manageEvents.setText("Manage Events");
        manageSpecTickets.setText("Manage Special Tickets");
        userControls.setText("User Controls");

        manageUsers.setMinWidth(container.getMinWidth() - 20);
        manageUsers.setMinHeight((container.getMinHeight() / 3) - 30);

        manageEvents.setMinWidth(manageUsers.getMinWidth());
        manageEvents.setMinHeight(manageUsers.getMinHeight());

        manageSpecTickets.setMinWidth(manageEvents.getMinWidth());
        manageSpecTickets.setMinHeight(manageEvents.getMinHeight());

        buttonContainer.getChildren().addAll(manageUsers, manageEvents, manageSpecTickets);
        buttonContainer.setLayoutX(container.getLayoutX() + 3);
        buttonContainer.setLayoutY(container.getLayoutY() + 50);
        buttonContainer.setMinHeight(container.getMinHeight() - 20);

        userControls.setLayoutX((container.getMinWidth() / 2) - 50);
        userControls.setLayoutY(container.getLayoutY() + 10);

        container.getChildren().addAll(userControls, buttonContainer);
    }

    private void displayEventsTableView(AnchorPane container) {
        //TODO
        container.getStyleClass().add("container");
    }

    private void HomeScreen(AnchorPane container) {
        ManageEventsScreen(container);
        //Change in sprint 2 depending on account type
    }


    private void ManageEventsScreen(AnchorPane container) {
        displayUserControls(container);
        //TODO Display: latest events and line
        displayEventsTableView(container);
        //TODO Display: New Event button
    }

    private void ManageSelectedEventScreen(AnchorPane container) {//when merged with backend, add an Event event to the constructor
        displayUserControls(container);
        //TODO Display: latest events and line
        displayEventsTableView(container);
        //TODO Display: New Event button
    }

    private void CreateEventScreen(AnchorPane container) {
        displayUserControls(container);
        //TODO Display: all elements according to figma
    }

    private void ManageTicketsScreen(AnchorPane container) {
        //TODO display event name,
        // generate tableview with 2 buttons (use and delete)
        // display search bar and go back button
    }

    private void ManageSpecialTicketsScreen(AnchorPane container) {

    }

    private void displayManageAccountScreen(AnchorPane container) {
        //sprint 2
    }


}
