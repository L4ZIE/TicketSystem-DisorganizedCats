package pl.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    }

    private void displayUserControls(AnchorPane container) {
        //TODO
        container.getStyleClass().add("controls-container");


    }

    private void displayEventsTableView(AnchorPane container) {
        //TODO
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
